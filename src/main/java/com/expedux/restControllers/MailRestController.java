package com.expedux.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expedux.services.EmailServices;
import com.expedux.utils.CommonFileUtils;

@CrossOrigin(origins = "*")
@RestController
public class MailRestController {
	
	@Autowired
	EmailServices mailservice;
	
	@GetMapping("apitestmail")
	public String simpleMail() {
		String result = "simple mail failure";

		String from = CommonFileUtils.fromMail;
		String to = CommonFileUtils.ccMail;
		String sub = "SMTP Test Mail";
		String body = "vanakkam da mapla eclipse la erunthu";
		System.out.println("API ATTACK ===>"+body);
		if (mailservice.simpleMailSender(from, to, sub, body)) {
			result = "simple mail sucess";
		}

		return result;
	}

}
