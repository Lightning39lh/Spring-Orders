package com.bootcamp.ApiFinal.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"email"})
		}
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String lastName;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String password;

	/**
	 * 
	 * @param email
	 * @param lastName
	 * @param password
	 * @param name
	 */
	public MyUser(String email, String lastName, String password, String name) {
		this.email = email;
		this.lastName = lastName;
		this.password = password;
		this.name = name;
	}
}
