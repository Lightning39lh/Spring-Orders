package com.bootcamp.ApiFinal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bootcamp.ApiFinal.Model.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
	
	@Query(value = "SELECT * FROM products WHERE code = :code AND state = true", nativeQuery = true)
	public Product findByCode(@Param("code") int code);

	public boolean existsByCodeAndState(int code, boolean state);

	public boolean existsByIdAndState(long id, boolean state);
	
	@Override
	@Query(value = "SELECT * FROM products WHERE state = true", nativeQuery = true)
	public List<Product> findAll();

	@Modifying
	@Query(value = "UPDATE products SET state = true WHERE id = :id" , nativeQuery = true)
	public void deleteById(@Param("id") long id);
}
