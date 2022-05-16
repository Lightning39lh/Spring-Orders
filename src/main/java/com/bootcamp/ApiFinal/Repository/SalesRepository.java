package com.bootcamp.ApiFinal.Repository;

import com.bootcamp.ApiFinal.Model.Client;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.ApiFinal.Model.Sale;


@Repository
public interface SalesRepository extends JpaRepository<Sale, Long> {
	
	public Sale findByTicket(int ticket);
	public boolean existsByTicket(int ticket);
	public List<Sale> findByClient(Client client);
}
