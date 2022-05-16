package com.bootcamp.ApiFinal.JwtUtils.Model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class JwtUser implements Serializable {

	private static final long serialVersionUID = -2443242029282358145L;
	@Getter @Setter private String email;
	@Getter @Setter private String password;
	
	public JwtUser(String em, String pw) {
		email = em;
		password = pw;
	}
}
