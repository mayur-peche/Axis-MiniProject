package com.mal.service;
import java.util.List;
import com.mal.entity.AnimeDb;

public interface AnimeDbService {
	AnimeDb addAnime(AnimeDb animedb);
	List<AnimeDb> getAllAnime();
	AnimeDb getAnimeByName(String animeName);
	AnimeDb updateAnimeById(int animeId,AnimeDb animedb );
	void deleteAnimeById(int Id);
	
}
