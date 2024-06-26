package com.expedux.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.expedux.services.MyUserDetailsService;

import jakarta.servlet.DispatcherType;
@Configuration
@EnableWebSecurity
public class MySecurityConfig {

	@Autowired
    private MyUserDetailsService userDetailsService;

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println("=====>> configure(AuthenticationManagerBuilder) <<======");
        auth.authenticationProvider(myAuthenticationProvider());
    }
    
    @Bean
    public DaoAuthenticationProvider myAuthenticationProvider() {

    	System.out.println("====>> myAuthenticationProvider <<=====");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {

    	System.out.println("====>> passwordEncoder <<=====");
        return new BCryptPasswordEncoder();
    }
    
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance(); // Using NoOpPasswordEncoder
//    }

 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    	System.out.println("====>> filterChain(HttpSecurity) <<=====");

        http

        //Normal Authentication
			.authorizeHttpRequests(authorize -> authorize
					.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll() 
					.requestMatchers("/static/**", "/login", "/register","/resume").permitAll()
					.requestMatchers("/**").authenticated()
					.anyRequest().denyAll())
        
    	// username Authentication
//        	.authorizeHttpRequests((authorize) -> authorize
//            .requestMatchers("/resource/{name}").access(new WebExpressionAuthorizationManager("#name == authentication.name"))
//            .anyRequest().authenticated()
//        )
    	
    	//REGEX based username Authentication
//        	.authorizeHttpRequests((authorize) -> authorize
//            .requestMatchers(RegexRequestMatcher.regexMatcher("/resource/[A-Za-z0-9]+")).hasAuthority("USER")
//            .anyRequest().denyAll()
//        )
        
       //access permission Authentication
//	        .authorizeHttpRequests((authorize) -> authorize
//	            .requestMatchers(HttpMethod.GET).hasAuthority("read")
//	            .requestMatchers(HttpMethod.POST).hasAuthority("write")
//	            .anyRequest().denyAll()
//	        )
        
        //dispatcher handler
//        .authorizeHttpRequests((authorize) -> authorize
//            .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
//            .requestMatchers("/login").permitAll()
//            .anyRequest().denyAll()
//        )
        
		//Multiple implementations
//        .authorizeHttpRequests(authorize -> authorize                                  
//                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll() 
//    			.requestMatchers("/static/**", "/login", "/register").permitAll()         
//    			.requestMatchers("/admin/**").hasRole("ADMIN") 
//				.requestMatchers("/db/**").access(allOf(hasAuthority("db"), hasRole("ADMIN")))   
//    			.anyRequest().denyAll()                                                
//    		)
        
        	.csrf(csrf -> csrf.disable())
        	.formLogin(login -> login
        				.loginPage("/login")
        				.defaultSuccessUrl("/index")
        				.failureUrl("/error"))
            .logout(logout -> logout
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/loggedout"));

        return http.build();
    }
}
