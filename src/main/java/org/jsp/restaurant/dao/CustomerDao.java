package org.jsp.restaurant.dao;

import java.util.List;

import org.jsp.restaurant.dto.Customer;
import org.jsp.restaurant.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {
	
	@Autowired
	CustomerRepository repository;

	public Customer fetchByEmail(String email) {
		return repository.findByEmail(email);
	}

	public Customer fetchByMobile(long mobile) {
		return repository.findByMobile(mobile);
	}

	public void save(Customer customer) {
		repository.save(customer);
	}

	public Customer fetchById(int id) {
		return repository.findById(id).orElse(null);
	}
	public List<Customer> fetchAll() {
		return repository.findAll();
	}


}
