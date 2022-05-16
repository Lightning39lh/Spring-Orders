package com.bootcamp.ApiFinal.Controllers;

import com.bootcamp.ApiFinal.Model.Client;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.ApiFinal.Services.ClientsService;

@RestController
@RequestMapping("/api/clients")
public class ClientsController {
	
	@Autowired
	private ClientsService service;
	
	@GetMapping
	public ResponseEntity<List<Client>> getAll(){
		return ResponseEntity.status(200).body(service.getAll());
	}
	
	@PostMapping
	public ResponseEntity<String> save(@Validated @RequestBody Client client, BindingResult result){
		if(result.hasErrors()) return ResponseEntity.status(400).body("Incomplete data");
		if(!service.save(client)) return ResponseEntity.status(400).body("Client already exists");
		return ResponseEntity.status(201).body("Created email");
	}
	
	@PutMapping
	public ResponseEntity<String> update(@Validated @RequestBody Client client, BindingResult result){
		if(result.hasErrors()) return ResponseEntity.status(400).body("Incomplete data");
		if(!service.update(client)) return ResponseEntity.status(400).body("Client doesn't exists");
		return ResponseEntity.status(200).body("Updated client");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id ){
		if(!service.delete(id)) return ResponseEntity.status(400).body("Client doesn't exists");
		return ResponseEntity.status(200).body("Deleted client");
	}
}
