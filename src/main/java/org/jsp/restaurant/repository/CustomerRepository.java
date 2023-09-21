package org.jsp.restaurant.repository;

import org.jsp.restaurant.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByEmail(String email);

	Customer findByMobile(long mobile);

}
