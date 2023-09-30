package org.jsp.restaurant.service;

import org.jsp.restaurant.controller.AdminController;
import org.jsp.restaurant.dao.HotelDao;
import org.jsp.restaurant.dto.Customer;
import org.jsp.restaurant.dto.Hotel;
import org.jsp.restaurant.helper.AES;
import org.jsp.restaurant.helper.LoginHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminService {


	public String login(LoginHelper helper, ModelMap map, HttpSession session) {
		if (helper.getEmail().equals("rugved271998@gmail.com")) {
            if (helper.getPassword().equals("admin")) {
                session.setAttribute("admin", "admin");
                map.put("pass", "Login Success");
                return "AdminHome";
            } else {
                map.put("fail", "Incorrect Password");
                return "Admin";
            }
        } else {
            map.put("fail", "Incorrect Email");
            return "Admin";
        }
    }
		
}
