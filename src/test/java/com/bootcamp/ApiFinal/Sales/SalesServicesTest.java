package com.bootcamp.ApiFinal.Sales;

import com.bootcamp.ApiFinal.Model.Client;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bootcamp.ApiFinal.Model.Sale;
import com.bootcamp.ApiFinal.Repository.ClientsRepository;
import com.bootcamp.ApiFinal.Repository.SalesRepository;
import com.bootcamp.ApiFinal.Services.SalesService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalesServicesTest {

	@InjectMocks
	private SalesService service;
	
	@Mock
	private SalesRepository repositoryMock;
	
	@Mock
	private ClientsRepository rmc;
	
	private Sale sale = new Sale(1l, 2456, new Client());
	
	private Client client = sale.getClient();
	
	@Test
	public void GET_SALES() {
		when(repositoryMock.findAll()).thenReturn(new ArrayList<Sale>());
		List<Sale> sales = service.getAll();
		assertNotNull(sales);
		assertEquals(sales, new ArrayList<Sale>());
	}
	
	@Test
	public void SAVE_SALE() {
		when(repositoryMock.existsByTicket(sale.getTicket())).thenReturn(false);
		when(repositoryMock.save(sale)).thenReturn(sale);
		boolean result = service.save(sale);
		assertTrue(result);
	}
	
	@Test
	public void SAVE_SALE_FAIL() {
		when(repositoryMock.existsByTicket(sale.getTicket())).thenReturn(true);
		boolean result = service.save(sale);
		assertFalse(result);
	}
	
	@Test
	public void GET_BY_TICKET() {
		when(repositoryMock.existsByTicket(sale.getTicket())).thenReturn(true);
		when(repositoryMock.findByTicket(sale.getTicket())).thenReturn(sale);
		Sale sale2 = service.getByTicket(sale.getTicket());
		assertNotNull(sale2);
		assertEquals(sale2, sale);
	}
	
	@Test
	public void GET_BY_TICKET_FAIL() {
		when(repositoryMock.existsByTicket(sale.getTicket())).thenReturn(false);
		Sale sale2 = service.getByTicket(sale.getTicket());
		assertNull(sale2);
	}
	
	@Test
	public void GET_BY_CLIENT() {
		when(rmc.existsByDni(client.getDni())).thenReturn(true);
		when(repositoryMock.findByClient(client)).thenReturn(new ArrayList<Sale>());
		List<Sale> sales = service.getByClient(client.getDni());
		assertNotNull(sales);
		assertEquals(sales, new ArrayList<Sale>());
	}
	
	
	@Test
	public void GET_BY_CLIENT_FAIL() {
		when(rmc.existsByDni(client.getDni())).thenReturn(false);
		List<Sale> sales = service.getByClient(client.getDni());
		assertNull(sales);
	}
}
	
