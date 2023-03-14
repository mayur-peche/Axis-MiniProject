package com.mal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.mal.entity.UserDb;

public interface UserRepository extends JpaRepository<UserDb, Integer>{
	
	@Query("SELECT u FROM UserDb u WHERE u.status = :status")
	List<UserDb> findByStatus(String status);
}
