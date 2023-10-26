package org.jsp.restaurant.controller;

import org.jsp.restaurant.dto.Customer;
import org.jsp.restaurant.dto.ShoppingCart;
import org.jsp.restaurant.helper.LoginHelper;
import org.jsp.restaurant.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.razorpay.RazorpayException;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	Customer customer;

	@Autowired
	CustomerService service;

	@GetMapping
	public String customer() {
		return "Customer";
	}

	@GetMapping("/signup")
	public String loadSign(ModelMap map) {
		map.put("customer", customer);
		return "CustomerSignUp";
	}

	@PostMapping("/signup")
	public String signup(@Valid Customer customer, BindingResult result, ModelMap map) {
		if(result.hasErrors()) {
			return "CustomerSignUp";
		}else{
	     return service.signup(customer, map);
		}
	}
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam int id, @RequestParam int otp, ModelMap map) {
		return service.verifyOtp(id, otp, map);
	}
	@PostMapping("login")
	public String login(LoginHelper helper, ModelMap map, HttpSession session) {
		return service.login(helper, map, session);
	}
	 @GetMapping("/fetch-items")
		public String fetchItems(HttpSession session, ModelMap map) {
			Customer customer = (Customer) session.getAttribute("customer");
			if (customer!= null) {
				return service.fetchItems(customer, session, map);
			} else {
				map.put("neg", "Invalid Session");
				return "CustomerHome";
			}
		}
	    
	    @GetMapping("/home")
	    public String loadHome(HttpSession session, ModelMap map)
	    {
	    	Customer customer = (Customer) session.getAttribute("customer");
			if (customer!= null) {
				return "CustomerHome";
			} else {
				map.put("neg", "Invalid Session");
				return "CustomerSignUp";
			}
	    }
	    @GetMapping("/cart-add/{id}")
	    public String addToCart(HttpSession session, ModelMap map, @PathVariable int id) {
	    	Customer customer = (Customer) session.getAttribute("customer");
			if (customer!= null) {
				
				
				return service.addToCart(id, session, map, customer);
			} else {
				map.put("neg", "Invalid Session");
				return "CustomerSignUp";
			}
	    }
	    @GetMapping("/cart-remove/{id}")
	    public String removeFromCart(HttpSession session, ModelMap map, @PathVariable int id) {
	    	Customer customer = (Customer) session.getAttribute("customer");
	    	if(customer != null) {
	    		return service.removeFromCart(id, session, map, customer);
	    	}else {
	    		map.put("neg", "Invalid session");
	    		return "CustomerSignUp";
	    	}
	    }
	    @GetMapping("/view-cart")
	    public String viewCart(HttpSession session, ModelMap map) throws RazorpayException {
	    	Customer customer = (Customer) session.getAttribute("customer");
			if (customer!= null) {
				return service.viewCart(customer, session, map);
			} else {
				map.put("neg", "Invalid Session");
				return "CustomerHome";
			}
	    }
	    @PostMapping("/payment/{id}")
	    public String checkPayment(@RequestParam String razorpay_payment_id, @PathVariable int id, HttpSession session, ModelMap map) {
	    	Customer customer = (Customer) session.getAttribute("customer");
			if (customer!= null) {
				return service.checkPayment(razorpay_payment_id, id, customer, session, map);
			} else {
				map.put("neg", "Invalid Session");
				return "CustomerHome";
			}
	    }
	    @GetMapping("/fetch-order")
	    public String fetchOrders(HttpSession session, ModelMap modelMap){
	         Customer customer = (Customer) session.getAttribute("customer");
			if (customer != null) {
				return service.fetchOrders(modelMap,session,customer);
			} else {
				modelMap.put("neg", "Invalid Session");
				return "Main";
			}
	    }
	   
	    @GetMapping("/forgotpassword")
	    public String loadforgot() {
	    	return "CustomerForgotPassword";
	    }
}

