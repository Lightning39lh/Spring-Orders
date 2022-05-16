package com.bootcamp.ApiFinal.Model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales_details")
public class SaleDetails {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	private Sale sale;
	@ManyToOne
	private Product product;
	
	@NotNull
	private int items;
}
