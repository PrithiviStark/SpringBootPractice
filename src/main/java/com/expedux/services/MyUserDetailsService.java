package com.expedux.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.expedux.models.Users;
import com.expedux.repos.UserRepository;
import com.expedux.security.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	System.out.println("loadUserByUsername("+username +")");
        Users user = userRepository.findByUserName(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
    	System.out.println("userdetails from db=="+user.toString());
       
    	//--------------------------------------------------------------implement type one
        return new MyUserDetails(user);
        
        //---------------------------------------------------------------implement type two
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        user.getRoles().forEach(role -> {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        });
//        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), grantedAuthorities);
    
    }
}
