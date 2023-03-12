package com.mal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mal.dto.AnimeDbDto;
import com.mal.entity.AnimeDb;

public interface AnimeRepository  extends JpaRepository<AnimeDb, Integer>{

	
	@Query("SELECT a FROM AnimeDb a WHERE a.animeName = :animeName")
	AnimeDbDto findByAnimeName(String animeName);
	
	AnimeDbDto findByRanking(int ranking);
	
}
