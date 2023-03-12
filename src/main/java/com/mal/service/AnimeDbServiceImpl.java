package com.mal.service;

import java.util.*;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mal.dto.AnimeDbDto;
import com.mal.entity.AnimeDb;
import com.mal.exception.CustomConstraintViolationException;
import com.mal.repository.AnimeRepository;

@Service
public class AnimeDbServiceImpl implements AnimeDbService{

	@Autowired
	private AnimeRepository animeRepository;
	

	public AnimeDbServiceImpl(AnimeRepository animeRepository) {
		this.animeRepository = animeRepository;
	}

	@Override
	public AnimeDbDto addAnime(AnimeDbDto animeDbDto) {

	    // Check total episode is negative
	    if (animeDbDto.getTotalEpisodes() < 0) {
	        throw new CustomConstraintViolationException("Total episodes cannot be negative", null);
	    }

	    // Check if ranking is already present in AnimeDbDto
	    AnimeDbDto animeByRanking = animeRepository.findByRanking(animeDbDto.getRanking());
	    if (animeByRanking != null) {
	        throw new CustomConstraintViolationException("Ranking already exists in AnimeDb", null);
	    }

	    AnimeDb animeDb = convertToEntity(animeDbDto); // convert DTO to entity
	    animeDb = animeRepository.save(animeDb);
	    return convertToDto(animeDb); // convert entity to DTO
	}


	@Override
	public List<AnimeDbDto> getAllAnime() {
		List<AnimeDb> animedb = (List<AnimeDb>)animeRepository.findAll();
		List<AnimeDbDto> animeDtos = new ArrayList<>();
		for(AnimeDb anime : animedb) {
			animeDtos.add(convertToDto(anime));
		}
		return animeDtos;
	}
	
	
	@Override
	public AnimeDbDto updateAnimeById(int id, AnimeDbDto animeDbDto) {
	    Optional<AnimeDb> optionalAnime = animeRepository.findById(id);
	    if (optionalAnime.isPresent()) {
	        AnimeDb existingAnime = optionalAnime.get();
	        existingAnime.setAnimeName(animeDbDto.getAnimeName());
	        existingAnime.setGlobalRatings(animeDbDto.getGlobalRatings());
	        existingAnime.setRanking(animeDbDto.getRanking());
	        existingAnime.setTotalEpisodes(animeDbDto.getTotalEpisodes());
	        AnimeDb updatedAnime = animeRepository.save(existingAnime);
	        return convertToDto(updatedAnime); // convert entity to DTO
	    } else {
	        throw new EntityNotFoundException("Anime not found with id: " + id);
	    }
	}

	
	@Override
	public String deleteAnimeById(int Id) {
	    Optional<AnimeDb> optionalAnime = animeRepository.findById(Id);
	    
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
	public AnimeDbDto getAnimeByName(String animeName) {
		AnimeDbDto anime = animeRepository.findByAnimeName(animeName);
	    if (anime == null) {
	        throw new EntityNotFoundException("Anime not found with name: " + animeName);
	    }
	    return anime;
	}
	
	private AnimeDb convertToEntity(AnimeDbDto animeDbDto) {
	    AnimeDb animeDb = new AnimeDb();
	    animeDb.setId(animeDbDto.getId());
	    animeDb.setAnimeName(animeDbDto.getAnimeName());
	    animeDb.setGlobalRatings(animeDbDto.getGlobalRatings());
	    animeDb.setRanking(animeDbDto.getRanking());
	    animeDb.setTotalEpisodes(animeDbDto.getTotalEpisodes());
	    return animeDb;
	}

	private AnimeDbDto convertToDto(AnimeDb animeDb){
		AnimeDbDto animedbdto = new AnimeDbDto();
		animedbdto.setId(animeDb.getId());
		animedbdto.setAnimeName(animeDb.getAnimeName());
		animedbdto.setGlobalRatings(animeDb.getGlobalRatings());
		animedbdto.setRanking(animeDb.getRanking());
		animedbdto.setTotalEpisodes(animeDb.getTotalEpisodes());
		return animedbdto;
	}

}