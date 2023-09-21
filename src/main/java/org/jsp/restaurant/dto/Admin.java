package org.jsp.restaurant.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Component
public class Admin {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
int id;
String email;
String password;
}
