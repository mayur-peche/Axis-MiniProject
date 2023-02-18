package com.mal.service;
import java.util.List;
import com.mal.entity.AnimeDb;
import com.mal.exception.AnimeNotFoundException;

public interface AnimeDbService {
	
	AnimeDb addAnime(AnimeDb animedb);
	AnimeDb getAnimeById(int animeId);
	AnimeDb updateAnimeById(int animeId,AnimeDb animedb );
	String deleteAnimeById(int animeId);
	List<AnimeDb> getAllAnimes();
}
