package com.mal.service;
import java.util.List;

import com.mal.dto.AnimeDbDto;

public interface AnimeDbService {
	AnimeDbDto addAnime(AnimeDbDto animeDbDto);
	List<AnimeDbDto> getAllAnime();
	AnimeDbDto getAnimeByName(String animeName);
	AnimeDbDto getAnimeById(int id);
	AnimeDbDto updateAnimeById(int animeId,AnimeDbDto animeDbDto );
	String deleteAnimeById(int Id);
	AnimeDbDto getAnimeDto(int id);
	
}
