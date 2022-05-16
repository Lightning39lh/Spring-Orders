package com.bootcamp.ApiFinal.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.ApiFinal.Model.SaleDetails;
import com.bootcamp.ApiFinal.Services.SalesDetailsService;

import Requests.SaleDetailsRequest;

@RestController
@RequestMapping("/api/salesDetail")
public class SalesDetailController {

	@Autowired
	private SalesDetailsService service;
	
	@GetMapping
	public ResponseEntity<List<SaleDetails>> getAll(){
		return ResponseEntity.status(200).body(service.getAll());
	}
	
	@PostMapping
	public ResponseEntity<String> save(@Validated @RequestBody SaleDetailsRequest saleRequest, BindingResult result){
		if(result.hasErrors()) return ResponseEntity.status(400).body("Incomplete data");
		int codeProduct = saleRequest.getProductCode();
		int saleTicket = saleRequest.getSaleTicket();
		int items = saleRequest.getItems();
		if(!service.save(codeProduct, saleTicket, items)) return ResponseEntity.status(400).body("Fail to save a sale detail");
		return ResponseEntity.status(201).body("Saved sale detail");
	}
}
