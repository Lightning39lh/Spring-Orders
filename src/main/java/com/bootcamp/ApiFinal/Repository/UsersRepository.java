package com.bootcamp.ApiFinal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.ApiFinal.Model.MyUser;

@Repository
public interface UsersRepository extends JpaRepository<MyUser, Long> {
	public boolean existsByEmail(String email);
	public MyUser findByEmail(String email);
}
