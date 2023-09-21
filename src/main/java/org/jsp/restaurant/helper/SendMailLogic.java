 package org.jsp.restaurant.helper;

import org.jsp.restaurant.dto.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendMailLogic {
	
	@Autowired
	JavaMailSender mailSender;
	
	public void send(Hotel hotel) {
		MimeMessage mess=mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mess);
		try {
			helper.setTo(hotel.getEmail());
			helper.setSubject("Verify Your otp");
			helper.setText("Hello "+hotel.getName() + "<h1> "+"<h3>otp  is  "+ hotel.getOtp()+"</h1>", true);
			mailSender.send(mess);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
