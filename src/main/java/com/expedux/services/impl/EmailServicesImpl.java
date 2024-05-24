package com.expedux.services.impl;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.expedux.controllers.LoginController;
import com.expedux.services.EmailServices;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServicesImpl implements EmailServices {

	private static final Logger logger= LoggerFactory.getLogger(LoginController.class);

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	VelocityEngine vEngine;

	@SuppressWarnings("finally")
	@Override
	public boolean simpleMailSender(String fromAddress, String toAddress, String mailSubject, String mailBody) {

		boolean result = false;

		try {
			SimpleMailMessage mail = new SimpleMailMessage();

			mail.setFrom(fromAddress);
			mail.setTo(toAddress);
			mail.setSubject(mailSubject);
			mail.setText(mailBody);

			mailSender.send(mail);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public boolean sampleVelocitySender(String from, String to, String subject, Map<String, Object> model) {
		boolean result = false;

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);

			StringWriter stringWriter = new StringWriter();
			VelocityContext context = new VelocityContext(model);
			Template template = vEngine.getTemplate("templates/vm/sampleVelocity.vm");
			System.out.println(template.getName());
			template.merge(context, stringWriter);
			String text = stringWriter.toString();
			helper.setText(text, true);
			
			mailSender.send(mimeMessage);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public boolean sampleVelocitySender(String from, String to, String subject,
			Map<String, Object> model, List<MultipartFile> files) {
	
		boolean result = false;

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);

			StringWriter stringWriter = new StringWriter();
			VelocityContext context = new VelocityContext(model);
			Template template = vEngine.getTemplate("templates/vm/sampleVelocity.vm");
			logger.info(template.getName());
			
			template.merge(context, stringWriter);
			String text = stringWriter.toString();
			helper.setText(text, true);
			
			for (MultipartFile file : files) {
				System.out.println("=======>" + file.getOriginalFilename());
				System.out.println("=======>" + file.getSize());
				helper.addAttachment(file.getOriginalFilename(), file);// Add the attachment directly from the MultipartFile
            }
			
			mailSender.send(mimeMessage);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}

}
