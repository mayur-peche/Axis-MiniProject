package com.mal.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mal.entity.AnimeDb;
import com.mal.exception.AnimeNotFoundException;
import com.mal.repository.AnimeRepository;


@Service
public class AnimeDbServiceImpl implements AnimeDbService{
	
	@Autowired
	AnimeRepository animeRepository;

	@Override
	public AnimeDb addAnime(AnimeDb animedb) {
		return animeRepository.save(animedb);
	}

	@Override
	public List<AnimeDb> getAllAnimes() {
		return animeRepository.findAll();
	}

	@Override
	public AnimeDb getAnimeById(int animeId) {  
		Optional<AnimeDb> anm = animeRepository.findById(animeId);
		
        if (anm.isPresent()) 
            return anm.get();
        else 
            throw new AnimeNotFoundException("Anime not found with id");
	}

	@Override
	public AnimeDb updateAnimeById(int animeId, AnimeDb animedb) {
Optional<AnimeDb> anm = animeRepository.findById(animeId);
		
        if (anm.isPresent()) 
            return animeRepository.save(animedb);
        else 
            throw new AnimeNotFoundException("Anime not found with id");
	}
	

	@Override
	public String deleteAnimeById(int animeId) {
Optional<AnimeDb>	anm =  animeRepository.findById(animeId);
		
		if(anm.isPresent())
		{
			animeRepository.deleteById(animeId);
			return "anime deleted";
		}
		else
			throw new AnimeNotFoundException("no anime present to delete");
	}

	
	
}