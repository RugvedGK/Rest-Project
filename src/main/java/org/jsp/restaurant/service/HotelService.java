package org.jsp.restaurant.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsp.restaurant.dao.FoodItemDao;
import org.jsp.restaurant.dao.HotelDao;
import org.jsp.restaurant.dto.FoodItem;
import org.jsp.restaurant.dto.Hotel;
import org.jsp.restaurant.helper.AES;
import org.jsp.restaurant.helper.LoginHelper;
import org.jsp.restaurant.helper.SendMailLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Service
@Component
public class HotelService {

	@Autowired
	HotelDao dao;
	
	@Autowired
	SendMailLogic logic;
	
	@Autowired
	FoodItemDao fooditemDao;
	
	public String register(Hotel hotel, ModelMap map) {
	Hotel hotel1=dao.fetchByEmail(hotel.getEmail());
	Hotel hotel2 = dao.fetchByMobile(hotel.getMobile());
	if(hotel1 == null && hotel2 == null) {
		int otp = new Random().nextInt(1000,9999);
		hotel.setOtp(otp);
		//logic.send(hotel);
		hotel.setPassword(AES.encrypt(hotel.getPassword(), "123"));
		dao.save(hotel);
		map.put("id", hotel.getId());
		return "HotelVerifyOtp";
	}
		else {
		map.put("neg", "Email and phone Number should not be repeted");
		return "HotelRegister";
	}
	}

	public String verifyOtp(int id, int otp, ModelMap map) {
		Hotel hotel = dao.fetchById(id);
		if(hotel.getOtp() == otp) {
			hotel.setStatus(true);
			dao.save(hotel);
			map.put("pos", "Otp varify Successfully");
			return "Hotel";
		}else
		{
			map.put("neg", "Otp mismatch");
			map.put("id", hotel.getId());
			return "HotelVerifyOtp";
		}
	}

	public String login(LoginHelper helper, ModelMap map, HttpSession session) {
		Hotel hotel = dao.fetchByEmail(helper.getEmail());
		if(hotel == null) {
			map.put("neg", "Invalid Email");
			return "Hotel";
		}else
			if(AES.decrypt(hotel.getPassword(), "123").equals(helper.getPassword())) {
				session.setAttribute("hotel", hotel);
				map.put("pos", "Valid Email");
				return "HotelHome";
			}else {
					map.put("neg", "Invalid Password");
		            return "Hotel";
				}
		
	}

	public String addItem(FoodItem foodItem, MultipartFile image, ModelMap map, HttpSession session, Hotel hotel) throws IOException {
		byte[] picture = new byte[image.getInputStream().available()];
		image.getInputStream().read(picture);
		
		foodItem.setPicture(picture);
		List<FoodItem> list = hotel.getItems();
		if(list == null) 
			list = new ArrayList<FoodItem>();
			list.add(foodItem);
			hotel.setItems(list);
			dao.save(hotel);
			map.put("pos", "Item added successfully");
			return "HotelHome";
	}

	public String fetchItems(Hotel hotel, ModelMap map, HttpSession session) {
		List<FoodItem> items = hotel.getItems();
		if(items == null || items.isEmpty()) {
			map.put("neg", "no items");
            return "HotelHome";
    }
    else{
        map.put("items", items);
        return "HotelItems";
    }
}

	public String edit(int id, ModelMap map, Hotel hotel, HttpSession session) {
		FoodItem items = fooditemDao.fetchById(id);
		if(items == null) {
			map.put("neg", "Something Went Worng");
			return "Main";
		}else {
			map.put("items", items);
			return "EditItem";
		}
	}

	public String deleteProduct(int id, ModelMap map, Hotel hotel, HttpSession session) {
		FoodItem items = fooditemDao.fetchById(id);
		if(items == null) {
			map.put("neg", "Something Went Wrong");
			return "Main";
		}
		hotel.getItems().remove(items);
		dao.save(hotel);
		fooditemDao.delete(items);
		map.put("pos", "Product Delete Success");
		return fetchItems(dao.fetchById(hotel.getId()), map, session);
	}

	public String editProducts(FoodItem foodItem, MultipartFile image, ModelMap map, HttpSession session, Hotel hotel) throws IOException {
		byte[] picture = new byte[image.getInputStream().available()];
		image.getInputStream().read(picture);
		
		if(picture.length  == 0) {
			foodItem.setPicture(fooditemDao.fetchById(foodItem.getId()).getPicture());
		}else {
			foodItem.setPicture(picture);
		}
		fooditemDao.save(foodItem);
		map.put("pos", "Item Updated successfully");
		session.setAttribute("hotel", dao.fetchById(hotel.getId()));
		return fetchItems(dao.fetchById(hotel.getId()), map, session);
	}
}

