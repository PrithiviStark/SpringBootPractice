package com.expedux.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.expedux.dtos.UserDetailsDto;
import com.expedux.models.Roles;
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

	@Override
	public void saveRegistration(UserDetailsDto ud) {
		
		Users user=new Users();
		Set<Roles> roleSet= new HashSet<Roles>();

		String[] roleList = ud.getRoles().split(",");
				
		for (String role : roleList) {
			Roles roles=new Roles();
			roles.setRoleName(role);
			roleSet.add(roles);
		}
		
		user.setUserName(ud.getUserName());
		user.setUserPassword(new BCryptPasswordEncoder().encode(ud.getUserPassword()));
		user.setRoles(roleSet);
		
		userRepo.save(user);
		
	}
	
}