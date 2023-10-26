package org.jsp.restaurant.repository;

import org.jsp.restaurant.dto.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentDetails, Integer>{

}
