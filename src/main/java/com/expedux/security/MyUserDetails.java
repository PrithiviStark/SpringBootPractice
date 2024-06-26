package com.expedux.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.expedux.models.Roles;
import com.expedux.models.Users;

public class MyUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private final String password;
	private final String username;
	private final Set<Roles> roles;

	public MyUserDetails(Users user) {

    	System.out.println("=======MyUserDetails("+user+")");
		this.password=user.getUserPassword();
		this.username=user.getUserName();
		this.roles=user.getRoles();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		  return roles.stream()
	                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
	                .collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
