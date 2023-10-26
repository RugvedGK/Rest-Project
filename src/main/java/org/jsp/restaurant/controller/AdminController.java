package org.jsp.restaurant.controller;

import org.jsp.restaurant.helper.LoginHelper;
import org.jsp.restaurant.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService service;

	@GetMapping()
	public String admin()
	{
		return "Admin";
	}
	
	@PostMapping("login")
	public String adminLogin(LoginHelper helper, ModelMap map, HttpSession session) {
		return service.login(helper, map, session);
	}

	@GetMapping("/fetch-items")
	public String fetchItems(HttpSession session, ModelMap map) {
		String admin = (String) session.getAttribute("admin");
		if (admin != null) {
			return service.fetchItems(session, map);
		} else {
			map.put("neg", "Invalid Session");
			return "HotelLogin";
		}
	
}
	@GetMapping("/product-status/{id}")
	public String changeStatus(@PathVariable int id, HttpSession session, ModelMap map) {
		String admin = (String) session.getAttribute("admin");
		if (admin != null) {
			return service.changeStatus(id, session, map);
		} else {
			map.put("neg", "Invalid Session");
			return "HotelLogin";
		}
	}
	@GetMapping("/fetch-hotels")
	public String fetchHotels(HttpSession session, ModelMap map) {
		String admin = (String) session.getAttribute("admin");
		if (admin != null) {
			return service.fetchHotels(session, map);
		} else {
			map.put("neg", "Invalid Session");
			return "HotelLogin";
		}
	}

	@GetMapping("/fetch-customers")
	public String fetchCustomers(HttpSession session, ModelMap map) {
		String admin = (String) session.getAttribute("admin");
		if (admin != null) {
			return service.fetchCustomers(session, map);
		} else {
			map.put("neg", "Invalid Session");
			return "HotelLogin";
		}
	}
}
