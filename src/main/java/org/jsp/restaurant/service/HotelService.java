package org.jsp.restaurant.service;

import java.util.Random;

import org.jsp.restaurant.dao.HotelDao;
import org.jsp.restaurant.dto.Hotel;
import org.jsp.restaurant.helper.AES;
import org.jsp.restaurant.helper.LoginHelper;
import org.jsp.restaurant.helper.SendMailLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;


@Service
@Component
public class HotelService {

	@Autowired
	HotelDao dao;
	
	@Autowired
	SendMailLogic logic;
	
	public String register(Hotel hotel, ModelMap map) {
	Hotel hotel1=dao.fetchByEmail(hotel.getEmail());
	Hotel hotel2 = dao.fetchByMobile(hotel.getMobile());
	if(hotel1 == null && hotel2 == null) {
		int otp = new Random().nextInt(1000,9999);
		hotel.setOtp(otp);
		logic.send(hotel);
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

	public String login(LoginHelper helper, ModelMap map) {
		Hotel hotel = dao.fetchByEmail(helper.getEmail());
		if(hotel == null) {
			map.put("neg", "Invalid Email");
			return "Hotel";
		}else
			if(AES.decrypt(hotel.getPassword(), "123").equals(helper.getPassword())) {
				map.put("pos", "Valid Email");
				return "HotelHome";
			}else {
					map.put("neg", "Invalid Password");
		            return "Hotel";
				}
		
	}
}

