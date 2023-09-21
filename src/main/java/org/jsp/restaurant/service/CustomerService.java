package org.jsp.restaurant.service;

import java.util.Random;

import org.jsp.restaurant.dao.CustomerDao;
import org.jsp.restaurant.dto.Customer;
import org.jsp.restaurant.helper.AES;
import org.jsp.restaurant.helper.CustomerSendMailLogic;
import org.jsp.restaurant.helper.LoginHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import jakarta.validation.Valid;

@Service
@Component
public class CustomerService {
	
	@Autowired
	CustomerDao dao;
	
	@Autowired
	CustomerSendMailLogic logic;

	public String signup(Customer customer, ModelMap map) {
		Customer customer1 = dao.fetchByEmail(customer.getEmail());
		Customer customer2 = dao.fetchByMobile(customer.getMobile());
		if(customer1 == null && customer2 == null) {
			int otp = new Random().nextInt(100000, 999999);
			customer.setOtp(otp);
			logic.send(customer);
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

	public String login(LoginHelper helper, ModelMap map) {
		Customer customer = dao.fetchByEmail(helper.getEmail());
		if(customer == null) {
			map.put("neg", "Invalid Email");
			return "Customer";
		}else
			if(AES.decrypt(customer.getPassword(), "123").equals(helper.getPassword())) {
				map.put("pos", "Valid Email");
				return "CustomerHome";
			}else {
				map.put("neg", "Invalid Password");
				return "Customer";
			}
	}
}
