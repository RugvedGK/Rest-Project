package org.jsp.restaurant.repository;

import org.jsp.restaurant.dto.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	Hotel findByEmail(String email);

	Hotel findByMobile(long mobile);

}
