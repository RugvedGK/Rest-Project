package org.jsp.restaurant.controller;

import org.jsp.restaurant.dto.Hotel;
import org.jsp.restaurant.helper.LoginHelper;
import org.jsp.restaurant.service.HotelService;
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
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	Hotel hotel;
	
	@Autowired 
	HotelService service;
	
	@GetMapping
	public String hotel() {
		return "Hotel";
	}
	
	@GetMapping("/register")
	public String loadRegister(ModelMap map) {
		map.put("hotel", hotel);
		return "HotelRegister";
	}
	@PostMapping("/register")
	public String register(@Valid Hotel hotel, BindingResult result, ModelMap map) {
		if(result.hasErrors()) {
		return "HotelRegister";
		}else {
			return service.register(hotel, map);
		}
	}
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam int id, @RequestParam int otp, ModelMap map) {
		return service.verifyOtp(id, otp, map);
	}
	@PostMapping("/login")
	public String login(LoginHelper helper, ModelMap map)
	{
		return service.login(helper, map);
	}
}
