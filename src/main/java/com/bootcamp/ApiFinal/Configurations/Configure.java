package com.bootcamp.ApiFinal.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Configure {

	@Bean
	public PasswordEncoder pe() {
		return new BCryptPasswordEncoder();
	}
}
