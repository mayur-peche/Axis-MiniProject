package com.mal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mal.entity.AnimeDb;

public interface AnimeRepository  extends JpaRepository<AnimeDb, Integer>{

	AnimeDb findByAnimeName(String animeName);
}
