package com.bootcamp.ApiFinal.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"dni"})
		}
)
public class Client {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private int dni;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String lastName;

	public Client(String n, String l, int dni) {
		this.name = n;
		this.lastName = l;
		this.dni = dni;
	}
}
