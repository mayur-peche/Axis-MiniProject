package com.mal.service;

import java.util.*;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mal.entity.AnimeDb;
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
	public List<AnimeDb> getAllAnime() {
		return animeRepository.findAll();
	}
	
	@Override
	  public AnimeDb updateAnimeById(int Id, AnimeDb animedb ) {
	        Optional<AnimeDb> optionalAnime = animeRepository.findById(Id);
	        if (optionalAnime.isPresent()) {
	        	AnimeDb existingAnime = optionalAnime.get();
	            existingAnime.setAnimeName(animedb.getAnimeName());
	            existingAnime.setGlobalRatings(animedb.getGlobalRatings());
	            existingAnime.setRanking(animedb.getRanking());
	            existingAnime.setTotalEpisodes(animedb.getTotalEpisodes());
	            animeRepository.save(existingAnime);
	            return existingAnime;
	        }else {
	        	throw new EntityNotFoundException("Anime not found with id: " + Id);
	        }
	    }
	
	@Override
	public void deleteAnimeById(int Id) {
		Optional<AnimeDb>	optionalAnime =  animeRepository.findById(Id);
		
		if(optionalAnime.isPresent())
		{
			animeRepository.deleteById(Id);
		}
		else {
			throw new EntityNotFoundException("Anime not found with id: " + Id);		
			}
	}

	@Override
	public AnimeDb getAnimeByName(String animeName) {
		
		 return animeRepository.findByAnimeName(animeName);
	}
}