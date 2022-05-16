package com.bootcamp.ApiFinal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.ApiFinal.Model.SaleDetails;

@Repository
public interface SalesDetailsRepository extends JpaRepository<SaleDetails, Long> {
}
