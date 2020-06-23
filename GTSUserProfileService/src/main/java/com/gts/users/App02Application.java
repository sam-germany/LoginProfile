package com.gts.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gts.users.security.AppProperties;

@SpringBootApplication
public class App02Application {

	public static void main(String[] args) {
		SpringApplication.run(App02Application.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext()
	{
		return new SpringApplicationContext();
	}

    @Bean("AppProperties")
    public AppProperties getAppProperties() 
    {
    	return new AppProperties();
    }
}
