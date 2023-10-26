package org.jsp.restaurant.dao;

import java.util.List;

import org.jsp.restaurant.dto.CustomerFoodItem;
import org.jsp.restaurant.dto.FoodItem;
import org.jsp.restaurant.dto.PaymentDetails;
import org.jsp.restaurant.dto.ShoppingCart;
import org.jsp.restaurant.repository.CartRepository;
import org.jsp.restaurant.repository.CustomerProductRepository;
import org.jsp.restaurant.repository.FoodItemRepository;
import org.jsp.restaurant.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FoodItemDao {

	@Autowired
	FoodItemRepository repository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CustomerProductRepository customerProductRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	public FoodItem fetchById(int id) {
		return repository.findById(id).orElse(null);
	}

	public void delete(FoodItem items) {
	repository.delete(items);
		
	}

	public void save(FoodItem foodItem) {
		repository.save(foodItem);
		
	}

	public List<FoodItem> fetchAll() {
		return repository.findAll();
	}

	public List<FoodItem> fetchAllApproved(){
		return repository.findByStatusTrue();
	}

	public void save(ShoppingCart cart) {
		cartRepository.save(cart);
		
	}

	public void delete(CustomerFoodItem customerProduct) {
		customerProductRepository.delete(customerProduct);
	}

	public PaymentDetails save(PaymentDetails payment) {
	    return paymentRepository.save(payment);
	}

	public PaymentDetails fetchDetails(int id) {
		return paymentRepository.findById(id).orElse(null);
	}

	public PaymentDetails saveDetails(PaymentDetails details) {
		return paymentRepository.save(details);		
	}

	}

