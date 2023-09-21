package org.jsp.restaurant.helper;

import org.jsp.restaurant.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class CustomerSendMailLogic {

	@Autowired
	JavaMailSender mailSender;
	
	public void send(Customer customer)
	{
		MimeMessage mess = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mess);
		try {
			helper.setTo(customer.getEmail());
			helper.setSubject("Verify Your Otp");
			helper.setText("Hello "+customer.getName() + "Otp is " + customer.getOtp(), true);
			mailSender.send(mess);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
