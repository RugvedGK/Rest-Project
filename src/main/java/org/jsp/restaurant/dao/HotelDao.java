package org.jsp.restaurant.dao;

import org.jsp.restaurant.dto.Hotel;
import org.jsp.restaurant.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HotelDao  {

	@Autowired
	HotelRepository repository;
	

	public Hotel fetchByEmail(String email) {
		return repository.findByEmail(email);
	}

	public Hotel fetchByMobile(long mobile) {
		return repository.findByMobile(mobile);
	}

	public void save(Hotel hotel) {
		repository.save(hotel);
		
	}

	public Hotel fetchById(int id) {
	return repository.findById(id).orElse(null);
		
	}
}
