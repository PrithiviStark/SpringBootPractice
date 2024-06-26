package com.expedux.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.expedux.dtos.UserDetailsDto;
import com.expedux.dtos.UserLoginDto;
import com.expedux.models.Users;
import com.expedux.services.LoginService;

import jakarta.validation.Valid;

@Controller
@EnableAspectJAutoProxy
public class LoginController {
	
	@Autowired
	LoginService service;
	
	private static final Logger logger= LoggerFactory.getLogger(LoginController.class);
	

	@GetMapping({"/login"})
	public String loginControl() {
		System.out.println("login page ------>>>>>");
		return "login";
	}
	
	@GetMapping("/loggedout")
	public String logoutControl() {
		System.out.println("logout page ------>>>>>");
		return "logout";
	}
	
	@GetMapping("/error")
	public String errorControl() {
		System.out.println("error page ------>>>>>");
		return "error";
	}

	@GetMapping("register")
	public ModelAndView register() {
		System.out.println("register page ------>>>>>");
		Users ul = new Users();
		ModelAndView mv =new ModelAndView();
		mv.addObject("userDetails", ul);
		mv.setViewName("register");
		return mv;
	}
	@PostMapping("registration")
	public ModelAndView registration(@Valid @ModelAttribute("userDetails")UserDetailsDto ud,BindingResult result ) {
		System.out.println("registartion processing ------>>>>>");
		ModelAndView mv =new ModelAndView();
		if(result.hasErrors()) {
			mv.setViewName("register");
		}
		else {
		service.saveRegistration(ud);
		System.out.println("registartion saved ------>>>>>");
		mv.setViewName("registerCompleted");
		}
		return mv;
	}
	
	
	
	@PostMapping({ "authenticate" })
	public ModelAndView authenticate(@Valid @ModelAttribute("userlogin") UserLoginDto ul,BindingResult result) {
		
		System.out.println(ul);
		logger.info(ul.toString());
		ModelAndView mv =new ModelAndView();
		
		if(result.hasErrors()) {
			mv.setViewName("login");
		}
		else if(ul!=null && !ul.getUserName().isEmpty() && !ul.getUserPassword().isEmpty()){
			String username=ul.getUserName();
			String password=ul.getUserPassword();
			System.out.println("user input===>"+username+"=="+password);
			logger.info("user input===>"+username+"=="+password);
			Users ud = service.findCredentials(username);
			if(ud != null && ud.getUserPassword()!= null) {
				System.out.println("database=====>"+ud.getUserName()+"=="+ud.getUserPassword());

				logger.info("database=====>"+ud.getUserName()+"=="+ud.getUserPassword());
				if(password.equalsIgnoreCase(ud.getUserPassword()))
				{
					mv.setViewName("redirect:/index");
				}	
				else {
					mv.setViewName("login");
				}
			}
			else {
				mv.setViewName("register");
			}
		}
		else {
			mv.setViewName("redirect :/login?error=true");
		}
		
		logger.info(mv.getViewName().toString());
		return mv;
	}
	
}
