package Requests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailsRequest {
	
	@NotNull
	private int productCode;

	@NotNull
	private int saleTicket;
	
	@NotNull
	private int items;
}
