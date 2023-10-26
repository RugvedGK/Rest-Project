package org.jsp.restaurant.controller;

import java.io.IOException;

import org.jsp.restaurant.dto.FoodItem;
import org.jsp.restaurant.dto.Hotel;
import org.jsp.restaurant.helper.LoginHelper;
import org.jsp.restaurant.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	Hotel hotel;
	
	@Autowired 
	HotelService service;
	
	@Autowired
	FoodItem foodItem;
	
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
	public String login(LoginHelper helper, ModelMap map, HttpSession session)
	{
		return service.login(helper, map, session);
	}
	@GetMapping("/add-item")
	public String loadHome(ModelMap map, HttpSession session) {
		Hotel hotel = (Hotel) session.getAttribute("hotel");
		if(hotel != null) {
			return "AddItem";
		}else {
			map.put("neg", "Invalid Session");
			return "HotelRegister";
		}
	}
	
	@PostMapping("/add-item")
	public String addItem(FoodItem foodItem, @RequestParam MultipartFile image, ModelMap map,  HttpSession session) throws IOException {
		Hotel hotel = (Hotel) session.getAttribute("hotel");
		if(hotel != null) {
				return service.addItem(foodItem, image, map, session, hotel);
		}else {
			map.put("neg", "Invalid Session");
			return "HotelRegister";
		}
	}
	 @GetMapping("/fetch-items")
	    public String fetchItems(HttpSession session, ModelMap map) {
	    Hotel hotel = (Hotel) session.getAttribute("hotel");
	        if (hotel != null) {
	           return service.fetchItems(hotel, map, session);
	        } else {
	            map.put("neg", "Invalid Session");
	            return "HotelRegister";
	        }
	    }
	 
	 @GetMapping("/delete-products/{id}")
	 public String deleteProduct(HttpSession session, @PathVariable int id, ModelMap map) {
		 Hotel hotel = (Hotel) session.getAttribute("hotel");
		 if(hotel != null) {
			 return service.deleteProduct(id, map, hotel, session);
		 }else {
			 map.put("neg", "Invalid Session");
			 return "HotelRegister";
		 }
	 }
	 
	 @GetMapping("/edit-products/{id}")
	 public String editProduct(ModelMap map, @PathVariable int id, HttpSession session) {
		 Hotel hotel = (Hotel) session.getAttribute("hotel");
		 if(hotel != null) {
			 return service.edit(id, map, hotel, session);
		 }else {
			 map.put("neg", "Invalid Session");
			 return "HotelRegister";
		 }
	 }
	 
	 @PostMapping("/edit-item")
	 public String editItem(FoodItem foodItem,@RequestParam MultipartFile image, ModelMap map, HttpSession session) throws IOException {
		 Hotel hotel = (Hotel) session.getAttribute("hotel");
		 if(hotel != null) {
			 return service.editProducts(foodItem, image, map, session, hotel);
		 }else {
			 map.put("neg", "Invalid Session");
			 return "HotelRegister";
		 }
		 
	 }
	 
	 @GetMapping("/home")
	 public String home(HttpSession session, ModelMap map) {
		 Hotel hotel = (Hotel) session.getAttribute("hotel");
		 if(hotel != null) {
			 return "HotelHome";
		 }else {
			 map.put("neg", "Invalid Session");
			 return "HotelRegister";
		 }
	 }
}	

