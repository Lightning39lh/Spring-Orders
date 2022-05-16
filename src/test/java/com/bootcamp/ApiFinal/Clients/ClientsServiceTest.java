package com.bootcamp.ApiFinal.Clients;

import com.bootcamp.ApiFinal.Model.Client;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bootcamp.ApiFinal.Repository.ClientsRepository;
import com.bootcamp.ApiFinal.Services.ClientsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientsServiceTest {
	
	@InjectMocks
	private ClientsService service;
	
	@Mock
	private ClientsRepository repository;
	
	private Client client1 = new Client("Carlos", "Sanchez", 32345657);
	
	private Client client2 = new Client(2, 43566879, "Raul", "Fernandez");
		
	@Test
	public void SELECT_CLIENTS() {
		when(repository.findAll()).thenReturn(new ArrayList<Client>());
		List<Client> clients = service.getAll();
		assertNotNull(clients);
	}
	
	@Test
	public void SAVE_CLIENT() {
		when(repository.existsByDni(client1.getDni())).thenReturn(false);
		when(repository.save(client1)).thenReturn(null);
		boolean result = service.save(client1);
		assertTrue(result);
	}
	
	@Test
	public void SAVE_CLIENT_FAIL() {
		when(repository.existsByDni(client1.getDni())).thenReturn(true);
		when(repository.save(client1)).thenReturn(null);
		boolean result = service.save(client1);
		assertFalse(result);
	}
	
	@Test
	public void UPDATE_CLIENT() {
		when(repository.existsById(client2.getId())).thenReturn(true);
		when(repository.save(client2)).thenReturn(null);
		boolean result = service.update(client2);
		assertTrue(result);
	}
	
	@Test
	public void UPDATE_CLIENT_FAIL() {
		when(repository.existsById(client2.getId())).thenReturn(false);
		when(repository.save(client2)).thenReturn(null);
		boolean result = service.update(client2);
		assertFalse(result);
	}
	
	@Test
	public void DELETE_CLIENT() {
		when(repository.existsById(client2.getId())).thenReturn(true);
		boolean result = service.delete(client2.getId());
		assertTrue(result);
	}
	
	@Test
	public void DELETE_CLIENT_FAIL() {
		when(repository.existsById(client2.getId())).thenReturn(false);
		boolean result = service.delete(client2.getId());
		assertFalse(result);
	}
}
