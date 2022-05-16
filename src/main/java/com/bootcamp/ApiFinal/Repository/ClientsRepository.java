package com.bootcamp.ApiFinal.Repository;

import com.bootcamp.ApiFinal.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {
	public boolean existsByDni(int dni);
	public Client findByDni(int dni);
}
