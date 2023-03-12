package com.mal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mal.dto.UserDbDto;
import com.mal.entity.UserDb;

public interface UserRepository extends JpaRepository<UserDb, Integer>{
	List<UserDbDto> getAnimeByStatus(String status);
}
