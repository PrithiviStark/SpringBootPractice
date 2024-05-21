package com.expedux.utils;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ServiceAspects {
	
	@AfterReturning(" execution (public * com.expedux.services.impl.LoginServiceImpl.findCredentials(String))")
	public void loggingOne() {

		System.out.println("====================================");
		System.out.println("logged in using services with aspect");
		System.out.println("====================================");
	}

}
