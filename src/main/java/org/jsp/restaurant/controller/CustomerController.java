package org.jsp.restaurant.controller;

import org.jsp.restaurant.dto.Customer;
import org.jsp.restaurant.helper.LoginHelper;
import org.jsp.restaurant.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String signup(@Valid Customer customer, BindingResult result,ModelMap map) {
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
	public String login(LoginHelper helper, ModelMap map) {
		return service.login(helper, map);
	}
}

