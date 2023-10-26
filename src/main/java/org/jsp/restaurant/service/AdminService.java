package org.jsp.restaurant.service;

import java.util.List;

import org.jsp.restaurant.controller.AdminController;
import org.jsp.restaurant.dao.CustomerDao;
import org.jsp.restaurant.dao.FoodItemDao;
import org.jsp.restaurant.dao.HotelDao;
import org.jsp.restaurant.dto.Customer;
import org.jsp.restaurant.dto.FoodItem;
import org.jsp.restaurant.dto.Hotel;
import org.jsp.restaurant.helper.AES;
import org.jsp.restaurant.helper.LoginHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminService {
	
	@Autowired
	FoodItemDao fooditemDao;
	
	@Autowired
	HotelDao hotelDao;
	
	@Autowired
	CustomerDao customerDao;
	

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

	public String fetchItems(HttpSession session, ModelMap map) {
	List<FoodItem> items = fooditemDao.fetchAll();
	if(items == null || items.isEmpty()) {
		map.put("neg", "No Items");
		return "AdminHome";
	}else {
		map.put("items", items);
		return "AdminItems";
	}
	}

	public String changeStatus(int id, HttpSession session, ModelMap map) {
		FoodItem foodItem = fooditemDao.fetchById(id);
		if (foodItem == null) {
			map.put("neg", "Something went wrong");
			return "Main";
		} else {
			if (foodItem.isStatus())
				foodItem.setStatus(false);
			else
				foodItem.setStatus(true);

			fooditemDao.save(foodItem);
			map.put("pos", "Status Changed Success");
			return fetchItems(session, map);
		}
	}

	public String fetchHotels(HttpSession session, ModelMap map) {
		List<Hotel> hotels = hotelDao.fetchAll();
		if (hotels == null || hotels.isEmpty()) {
			map.put("neg", "no hotels");
			return "AdminHome";
		} else {
			map.put("hotels", hotels);
			return "AdminHotels";
		}
	}

	public String fetchCustomers(HttpSession session, ModelMap map) {
		List<Customer> customers = customerDao.fetchAll();
		if (customers == null || customers.isEmpty()) {
			map.put("neg", "no customers");
			return "AdminHome";
		} else {
			map.put("customers", customers);
			return "AdminCustomers";
		}
	}
		
}
