package org.jsp.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@GetMapping("/")
	public String loadMain() {
		return "Main";
	}
	@GetMapping("/logout")
	public String logout(ModelMap map, HttpSession session) {
		session.invalidate();
		map.put("pos", "Logout Success");
		return "Main";
	}
}
