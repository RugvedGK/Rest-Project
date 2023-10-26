package org.jsp.restaurant.repository;

import org.jsp.restaurant.dto.CustomerFoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProductRepository extends JpaRepository<CustomerFoodItem, Integer> {

}
