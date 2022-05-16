package com.bootcamp.ApiFinal.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bootcamp.ApiFinal.Model.MyUser;
import com.bootcamp.ApiFinal.Repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<MyUser> getAll(){
		return repository.findAll();
	}
	
	public boolean save(MyUser user) {
		if(repository.existsByEmail(user.getEmail())) return false;
		String passwordHashed = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordHashed);
		repository.save(user);
		return true;
	}
	
	public boolean update(MyUser user) {
		if(!repository.existsById(user.getId())) return false;
		String passwordHashed = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordHashed);
		repository.save(user);
		return true;
	}
	
	public boolean delete(long id) {
		if(!repository.existsById(id)) return false;
		repository.deleteById(id);
		return true;
	}
}
