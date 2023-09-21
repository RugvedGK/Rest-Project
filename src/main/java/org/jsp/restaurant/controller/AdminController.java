package org.jsp.restaurant.controller;

import org.jsp.restaurant.dto.Admin;
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

	@GetMapping()
	public String admin()
	{
		return "Admin";
	}
	
}
