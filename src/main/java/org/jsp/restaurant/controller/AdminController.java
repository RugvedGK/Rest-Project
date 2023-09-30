package org.jsp.restaurant.controller;

import org.jsp.restaurant.helper.LoginHelper;
import org.jsp.restaurant.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
