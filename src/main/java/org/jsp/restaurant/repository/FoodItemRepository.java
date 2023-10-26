package org.jsp.restaurant.repository;

import java.util.List;

import org.jsp.restaurant.dto.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Integer>{

	List<FoodItem> findByStatusTrue();

	

}
