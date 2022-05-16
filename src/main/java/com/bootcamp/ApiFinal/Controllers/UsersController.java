package com.bootcamp.ApiFinal.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.ApiFinal.Model.MyUser;
import com.bootcamp.ApiFinal.Services.UsersService;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	@GetMapping
	public ResponseEntity<List<MyUser>> getAll(){
		return ResponseEntity.status(200).body(service.getAll());
	}
	
	
	@PostMapping
	public ResponseEntity<String> save(@RequestBody MyUser user){
		if(!service.save(user)) return ResponseEntity.status(400).body("The user already exists");
		return ResponseEntity.status(201).body("Created user");
	}
	
	@PutMapping
	public ResponseEntity<String> update(@RequestBody MyUser user){
		if(!service.update(user)) return ResponseEntity.status(400).body("The user doesn't exists");
		return ResponseEntity.status(200).body("Updated user");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id){
		if(!service.delete(id)) return ResponseEntity.status(400).body("The user doesn't exists");
		return ResponseEntity.status(200).body("deleted user");
	}
}
