package org.jsp.restaurant.repository;

import org.jsp.restaurant.dto.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<ShoppingCart, Integer> {

}
