package com.expedux.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expedux.models.Users;
import com.expedux.repos.UserRepository;
import com.expedux.services.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserRepository userRepo;

	@Override
	public Users findCredentials(String userName) {
		return userRepo.findByUserName(userName);
	}
	
}