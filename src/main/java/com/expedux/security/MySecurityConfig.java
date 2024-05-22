package com.expedux.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.expedux.services.MyUserDetailsService;
@Configuration
@EnableWebSecurity
public class MySecurityConfig {

	@Autowired
    private MyUserDetailsService userDetailsService;

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println("==================configure(AuthenticationManagerBuilder auth)");
        auth.authenticationProvider(myAuthenticationProvider());
    }
    
    @Bean
    public DaoAuthenticationProvider myAuthenticationProvider() {

    	System.out.println("===============myAuthenticationProvider()");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {

    	System.out.println("===============passwordEncoder()");
        return new BCryptPasswordEncoder();
    }
    
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance(); // Using NoOpPasswordEncoder
//    }

 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    	System.out.println("===============filterChain(HttpSecurity http)");

        http.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests(authorize -> authorize
//				      .requestMatchers("/login").permitAll()
//				      .anyRequest().authenticated())
        		.formLogin(login -> login
        				.loginPage("/login")
        				.defaultSuccessUrl("/register")
        				.failureUrl("/error"))
                .logout(logout -> logout
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/loggedout"));

        return http.build();
    }
}
