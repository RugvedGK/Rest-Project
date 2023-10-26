package org.jsp.restaurant.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;
import org.jsp.restaurant.dao.CustomerDao;
import org.jsp.restaurant.dao.FoodItemDao;
import org.jsp.restaurant.dto.Customer;
import org.jsp.restaurant.dto.CustomerFoodItem;
import org.jsp.restaurant.dto.CustomerOrder;
import org.jsp.restaurant.dto.FoodItem;
import org.jsp.restaurant.dto.PaymentDetails;
import org.jsp.restaurant.dto.ShoppingCart;
import org.jsp.restaurant.helper.AES;
import org.jsp.restaurant.helper.CustomerSendMailLogic;
import org.jsp.restaurant.helper.LoginHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.ToString;

@Service
@Component
public class CustomerService {
	
	@Autowired
	CustomerDao dao;
	
	@Autowired
	CustomerSendMailLogic logic;
	
	@Autowired
	FoodItemDao foodItemDao;
	
	@Autowired
	PaymentDetails details;

	public String signup(Customer customer, ModelMap map) {
		Customer customer1 = dao.fetchByEmail(customer.getEmail());
		Customer customer2 = dao.fetchByMobile(customer.getMobile());
		if(customer1 == null && customer2 == null) {
			int otp = new Random().nextInt(100000, 999999);
			customer.setOtp(otp);
			//logic.send(customer);
			customer.setPassword(AES.encrypt(customer.getPassword(), "123"));
			dao.save(customer);
			map.put("id", customer.getId());
			return "CustomerVerifyOtp";
		}
		else {
			map.put("neg", "Email and Phone Number Should not be repeated");
			return "CustomerSignUp";
		}
	}

	public String verifyOtp(int id, int otp, ModelMap map) {
		Customer customer = dao.fetchById(id);
		if(customer.getOtp() == otp) {
			customer.setStatus(true);
			dao.save(customer);
			map.put("pos", "Otp verify Successfully");
			return "Customer";
		}else {
			map.put("neg", "Otp mismatch");
			map.put("id", customer.getId());
			return "CustomerVerifyOtp";
		}
	}

	public String login(LoginHelper helper, ModelMap map, HttpSession session) {
		Customer customer = dao.fetchByEmail(helper.getEmail());
		if(customer == null) {
			map.put("neg", "Invalid Email");
			return "Customer";
		}else
			if(AES.decrypt(customer.getPassword(), "123").equals(helper.getPassword())) {
				session.setAttribute("customer", customer);
				map.put("pos", "Valid Email");
				return "CustomerHome";
			}else {
				map.put("neg", "Invalid Password");
				return "Customer";
			}
	}
	public String fetchItems(Customer customer, HttpSession session, ModelMap map) {
		List<FoodItem> items = foodItemDao.fetchAllApproved();
		if (items == null || items.isEmpty()) {
			map.put("neg", "no items");
			return "CustomerHome";
		} else 
		{
			map.put("items", items);
			return "CustomerItems";
		}
	}

	public String addToCart(int id, HttpSession session, ModelMap map, Customer customer) {
		FoodItem items = foodItemDao.fetchById(id);
		if(items != null) {
			if(items.getStock()>0) {
				ShoppingCart cart = customer.getCart();
				if(cart == null)
					cart=new ShoppingCart();
				List<CustomerFoodItem> customerFood = cart.getCustomerFood();
				if(customerFood == null) {
					customerFood = new ArrayList<CustomerFoodItem>();
				}
				boolean flag = true;
				for (CustomerFoodItem customerProduct : customerFood) {
					if (customerProduct.getName().equals(items.getName())) {
						customerProduct.setQuantity(customerProduct.getQuantity() + 1);
						customerProduct.setPrice(customerProduct.getPrice() + items.getPrice());
						flag = false;
						break;
					}
				}
				if (flag) {
					CustomerFoodItem customerProduct = new CustomerFoodItem();
					customerProduct.setName(items.getName());
					customerProduct.setDescription(items.getDescription());
					customerProduct.setPicture(items.getPicture());
					customerProduct.setPrice(items.getPrice());
					customerProduct.setQuantity(1);
					customerFood.add(customerProduct);
					cart.setCustomerFood(customerFood);
				}
				customer.setCart(cart);
				dao.save(customer);
				session.setAttribute("customer", dao.fetchById(customer.getId()));
				items.setStock(items.getStock()-1);
				foodItemDao.save(items);
				
				map.put("neg", "items Add to Cart");
			}else {
				map.put("neg", "Out of Stock");
			}
			return fetchItems(dao.fetchById(customer.getId()), session, map);
		}else {
			map.put("neg", "Something went Wrong");
			return "Main";
		}
	}

	public String removeFromCart(int id, HttpSession session, ModelMap map, Customer customer) {
		FoodItem foodItem = foodItemDao.fetchById(id);
		if(foodItem != null) {
			ShoppingCart cart = customer.getCart();
			if(cart == null) {
				map.put("neg", "No Items in Cart");
				return fetchItems(dao.fetchById(customer.getId()), session, map);
			}else {
				List<CustomerFoodItem> list = cart.getCustomerFood();
				if(list == null) {
					map.put("neg", "No Item in Cart");
					return fetchItems(dao.fetchById(customer.getId()), session, map);
				}else {
					CustomerFoodItem customerProduct = null;
					for(CustomerFoodItem customerProduct1 : list) {
						if(foodItem.getName().equals(customerProduct1.getName()));
						customerProduct = customerProduct1;
						break;
					}
				if(customerProduct == null) {
					map.put("neg", "No Items in Cart");
					return fetchItems(dao.fetchById(customer.getId()), session, map);
				}else {
					if(customerProduct.getQuantity()>1) {
						customerProduct.setQuantity(customerProduct.getQuantity() - 1);
						customerProduct.setPrice(customerProduct.getPrice() - foodItem.getPrice());
						foodItem.setStock(foodItem.getStock() + 1);
						foodItemDao.save(foodItem);
						foodItemDao.save(cart);
						foodItemDao.delete(customerProduct);
					}
					map.put("pos", "Item removed from Cart");
					session.setAttribute("customer", dao.fetchById(customer.getId()));
					return fetchItems(dao.fetchById(customer.getId()), session, map);

						}
			}
		}
	}else {
		map.put("neg", "Something went Wrong");
		return "Main";
	}
	
	}

	public String viewCart(Customer customer, HttpSession session, ModelMap map) throws RazorpayException {
		ShoppingCart cart = customer.getCart();
		if(cart == null) {
			map.put("neg", "No Items in Cart");
			return fetchItems(dao.fetchById(customer.getId()), session, map);
		}else {
			List<CustomerFoodItem> list = cart.getCustomerFood();
			if (list == null || list.isEmpty()) {
				map.put("neg", "No Items in Cart");
				return fetchItems(dao.fetchById(customer.getId()), session, map );
			} else {
				boolean flag = true;
				for (CustomerFoodItem customerProduct : list) {
					if (customerProduct.getQuantity() > 0)
						flag = false;
					break;
				}
				if (flag) {
					map.put("neg", "No Items in Cart");
					return fetchItems(dao.fetchById(customer.getId()), session, map);
				} else {
					double amount = 0;
					for (CustomerFoodItem customerProduct : list) {
						amount = amount + customerProduct.getPrice();
					}
			JSONObject object = new JSONObject();
			object.put("amount", (int) (amount * 100));
			
			object.put("currency", "INR");
			RazorpayClient client = new RazorpayClient("rzp_test_YbZ1C9xdzqY95O", "SX1DlEYGoZd2ZN0KwG6LZbMs");
			Order order = client.orders.create(object);
			PaymentDetails payment = new PaymentDetails();
			payment.setAmount(order.get("amount").toString());
			payment.setCurrency(order.get("currency").toString());
			payment.setPaymentId(null);
			payment.setOrderId(order.get("id").toString());
			payment.setStatus(order.get("status").toString());
			payment.setKeyDetails("rzp_test_YbZ1C9xdzqY95O");
			
			map.put("details", foodItemDao.save(payment));
			map.put("items", list);
			map.put("details", payment);
			session.setAttribute("customer", dao.fetchById(customer.getId()));
			return "ViewCart";
		}
				
			}
		}
	}

	public String checkPayment(String payment_id, int id, Customer customer, HttpSession session, ModelMap map) {
		if(payment_id != null) {
			PaymentDetails details = foodItemDao.fetchDetails(id);
            details.setPaymentId(payment_id);
            details.setStatus("Success");
            foodItemDao.saveDetails(details);

            List<CustomerOrder> orders = customer.getOrders();
            if (orders == null)
                orders = new ArrayList<CustomerOrder>();
            CustomerOrder customerOrder = new CustomerOrder();
            customerOrder.setDateTime(LocalDateTime.now());
            customerOrder.setPrice(Double.parseDouble(details.getAmount()) / 100);
            customerOrder.setList(customer.getCart().getCustomerFood());

            orders.add(customerOrder);
            customer.setOrders(orders);
            customer.setCart(null);
            dao.save(customer);
            session.setAttribute("customer", dao.fetchById(customer.getId()));
            map.put("pos", "Order Placed Success");
            return "CustomerHome";
        } else {
            map.put("neg", "Payment Not Done");
            return "CustomerHome";
        }
		}

	public String fetchOrders(ModelMap modelMap, HttpSession session, Customer customer) {
		 List<CustomerOrder> orders = customer.getOrders();
	        if (orders == null || orders.isEmpty()) {
	            modelMap.put("neg", "No Orders Found");
	            return "CustomerHome";
	        } else {
	            modelMap.put("orders", orders);
	            return "CustomerOrders";
	        }
	    }
	}


			
		


