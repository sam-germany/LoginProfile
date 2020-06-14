package com.gst.users.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	
	   private final BCryptPasswordEncoder bCryptPasswordEncoder;
		
   
	   
	   public WebSecurity(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}



	@Override
	    protected void configure(HttpSecurity http) throws Exception {
	             http.csrf().disable();
	              }
	   
	   

}
