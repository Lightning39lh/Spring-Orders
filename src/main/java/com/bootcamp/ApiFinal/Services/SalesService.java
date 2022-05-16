package com.bootcamp.ApiFinal.Services;

import com.bootcamp.ApiFinal.Model.Client;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.ApiFinal.Model.Sale;
import com.bootcamp.ApiFinal.Repository.ClientsRepository;
import com.bootcamp.ApiFinal.Repository.SalesRepository;

@Service
public class SalesService {
	
	@Autowired
	private SalesRepository repository;
	
	@Autowired 
	private ClientsRepository repositoryClient;
	
	public List<Sale> getAll(){
		return repository.findAll();
	}
	
	public Sale getByTicket(int ticket) {
		if(!repository.existsByTicket(ticket)) return null;
		return repository.findByTicket(ticket);
	}
	
	@Transactional
	public List<Sale> getByClient(int dni){
		if(!repositoryClient.existsByDni(dni)) return null;
		Client client = repositoryClient.findByDni(dni);
		return repository.findByClient(client);
	}
	
	public boolean save(Sale sale) {
		if(repository.existsByTicket(sale.getTicket())) return false;
		repository.save(sale);
		return true;
	}
}
