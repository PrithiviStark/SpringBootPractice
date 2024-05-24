package com.expedux.services;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface EmailServices {
	
	boolean simpleMailSender(String fromAddress,String toAddress,String mailSubject,String mailBody);
	
	boolean sampleVelocitySender(String fromAddress,String toAddress, String mailSubject, Map<String, Object> model);
	
	boolean sampleVelocitySender(String fromAddress,String toAddress, String mailSubject, Map<String, Object> model,List<MultipartFile> file);

}
