package com.bootcamp.ApiFinal.Model;





import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"ticket"})
		}
)
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private int ticket;
	
	@ManyToOne
	private Client client;
}
