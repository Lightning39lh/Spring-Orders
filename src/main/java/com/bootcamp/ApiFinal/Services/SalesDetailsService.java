package com.bootcamp.ApiFinal.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.ApiFinal.Model.Product;
import com.bootcamp.ApiFinal.Model.Sale;
import com.bootcamp.ApiFinal.Model.SaleDetails;
import com.bootcamp.ApiFinal.Repository.ProductsRepository;
import com.bootcamp.ApiFinal.Repository.SalesDetailsRepository;
import com.bootcamp.ApiFinal.Repository.SalesRepository;


@Service
public class SalesDetailsService {
	
	@Autowired
	private SalesDetailsRepository repository;
	
	@Autowired
	private ProductsRepository repositoryProduct;
	
	@Autowired
	private SalesRepository repositorySale;
	
	public List<SaleDetails> getAll(){
		return repository.findAll();
	}
	
	@Transactional
	public boolean save(int productCode, int saleTicket, int items) {
		if(
				(!repositoryProduct.existsByCodeAndState(productCode,  true)) ||
				(!repositorySale.existsByTicket(saleTicket))
		  ) return false;

		Product product = repositoryProduct.findByCode(productCode);
		Sale sale = repositorySale.findByTicket(saleTicket);
		
		int newStock = product.getStock() - items;
		if(newStock < 0) {
			repositoryProduct.deleteById(product.getId());
			return false;
		};
		
		product.setStock(newStock);
		repositoryProduct.save(product);
		
		SaleDetails saleDetail = new SaleDetails(0, sale, product, items);
		
		repository.save(saleDetail);
		return true;
	}

	

}
