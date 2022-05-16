package com.bootcamp.ApiFinal.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.ApiFinal.Model.Product;
import com.bootcamp.ApiFinal.Repository.ProductsRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepository repository;
	
	public List<Product> getAll(){
		return repository.findAll();
	}
	
	public boolean save(Product product) {
		if(repository.existsByCodeAndState(product.getCode(), true)) return false;
		product.setState(true);
		repository.save(product);
		return true;
	}
	
	public boolean update(Product product) {
		if(!repository.existsByIdAndState(product.getId(), true)) return false;
		product.setState(true);
		repository.save(product);
		return true;
	}
	
	public boolean delete(long id) {
		if(!repository.existsByIdAndState(id, true)) return false;
		repository.deleteById(id);
		return true;
	}
}
