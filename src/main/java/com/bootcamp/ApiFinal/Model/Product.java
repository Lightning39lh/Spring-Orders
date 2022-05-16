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
@NoArgsConstructor
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"code"})
		}
)
public class Product {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) 
	private long id;
	
	@NotNull
	private int code;
	
	@NotNull
	private int stock;
	
	@NotEmpty
	private String name;
	
	@NotNull
	private double price;
	
	@NotNull
	private boolean state;
	
	public Product(int c, int s, String n, double p) {
		code = c;
		stock = s;
		name = n;
		price = p;
		state = true;
	}
	
	public Product(int i,int c, int s, String n, double p) {
		id = i;
		code = c;
		stock = s;
		name = n;
		price = p;
		state = true;
	}
}
