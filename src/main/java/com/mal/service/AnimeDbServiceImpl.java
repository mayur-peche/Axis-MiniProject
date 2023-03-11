package com.mal.service;

import java.util.*;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mal.entity.AnimeDb;
import com.mal.exception.CustomConstraintViolationException;
import com.mal.repository.AnimeRepository;

@Service
public class AnimeDbServiceImpl implements AnimeDbService{

	@Autowired
	AnimeRepository animeRepository;

	@Override
	public AnimeDb addAnime(AnimeDb animedb) {
 
        // Check total episode is negative
        if (animedb.getTotalEpisodes() < 0 ) {
        	throw new CustomConstraintViolationException("Total episodes cannot be negative", null);
        }
        
        // Check if ranking is already present in AnimeDb
        AnimeDb animeByRanking = animeRepository.findByRanking(animedb.getRanking());
        if (animeByRanking != null) {
            throw new CustomConstraintViolationException("Ranking already exists in AnimeDb", null);
        }
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
	public String deleteAnimeById(int Id) {
		Optional<AnimeDb>	optionalAnime =  animeRepository.findById(Id);
		
		if(optionalAnime.isPresent())
		{
			animeRepository.deleteById(Id);
			return "anime deleted";
		}
		else {
			throw new EntityNotFoundException("Anime not found with id: " + Id);		
			}
	}

	@Override
	public AnimeDb getAnimeByName(String animeName) {
	    AnimeDb anime = animeRepository.findByAnimeName(animeName);
	    if (anime == null) {
	        throw new EntityNotFoundException("Anime not found with name: " + animeName);
	    }
	    return anime;
	}

}