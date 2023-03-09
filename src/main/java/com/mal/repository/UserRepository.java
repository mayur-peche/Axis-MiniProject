package com.mal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mal.entity.UserDb;

public interface UserRepository extends JpaRepository<UserDb, Integer>{

}
