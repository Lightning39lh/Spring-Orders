package com.bootcamp.ApiFinal.Services;

import com.bootcamp.ApiFinal.Model.Client;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.ApiFinal.Repository.ClientsRepository;

@Service
public class ClientsService {
	
	@Autowired
	private ClientsRepository repository;
	
	public List<Client> getAll(){
		return repository.findAll();
	}
	
	public Client getOne(int dni) {
		return repository.findByDni(dni);
	}
	
	public boolean save(Client client) {
		if(repository.existsByDni(client.getDni())) return false;
		repository.save(client);
		return true;
	}
	
	public boolean update(Client client) {
		if(!repository.existsById(client.getId())) return false;
		repository.save(client);
		return true;
	}
	
	public boolean delete(long id) {
		if(!repository.existsById(id)) return false;
		repository.deleteById(id);
		return true;
	}
}
