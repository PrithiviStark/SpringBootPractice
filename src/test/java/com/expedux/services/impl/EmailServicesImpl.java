//package com.expedux.services.impl;
//
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.mail.javamail.JavaMailSender;
//
//import com.expedux.services.EmailServices;
//
//public class EmailServicesImpl {
//	
//	@InjectMocks
//	EmailServices  mailServices;
//	
//	@Mock
//	JavaMailSender mailsender;
//	
//	@Test
//	void mailSenderShouldReturnTrue() {
//		assertTrue(mailServices.simpleMailSender("me","me","me","me"));
//	}
//}