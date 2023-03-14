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
    public AnimeDbDto getAnimeDto(int id) {
        Optional<AnimeDb> anime = animeRepository.findById(id);
        if (anime.isPresent()) {
            AnimeDb animeDb = anime.get();
            AnimeDbDto animeDbDto = new AnimeDbDto(animeDb.getId(), animeDb.getAnimeName(), animeDb.getRanking(),
                    animeDb.getGlobalRatings(), animeDb.getTotalEpisodes());
            return animeDbDto;
        } else {
            throw new EntityNotFoundException("Anime with ID " + id + " not found");
        }
    }

	@Override
	public AnimeDbDto addAnime(AnimeDbDto animeDbDto) {

		 AnimeDb animeDb = convertToEntity(animeDbDto); // convert DTO to entity
		 
	    // Check total episode is negative
	    if (animeDb.getTotalEpisodes() < 0) {
	        throw new CustomConstraintViolationException("Total episodes cannot be negative", null);
	    }

	    // Check if ranking is already present in AnimeDbDto
	    AnimeDb animeByRanking = animeRepository.findByRanking(animeDb.getRanking());
	    if (animeByRanking != null) {
	        throw new CustomConstraintViolationException("Ranking already exists in AnimeDb", null);
	    }

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
	    AnimeDb existingAnime = animeRepository.findById(id)
	        .orElseThrow(() -> new EntityNotFoundException("Anime not found with id: " + id));
	    existingAnime.setAnimeName(animeDbDto.getAnimeName());
	    existingAnime.setGlobalRatings(animeDbDto.getGlobalRatings());
	    existingAnime.setRanking(animeDbDto.getRanking());
	    existingAnime.setTotalEpisodes(animeDbDto.getTotalEpisodes());
	    AnimeDb updatedAnime = animeRepository.save(existingAnime);
	    return convertToDto(updatedAnime);
	}

	
	@Override
	public String deleteAnimeById(int Id) {
	    AnimeDb anime = animeRepository.findById(Id)
	        .orElseThrow(() -> new EntityNotFoundException("Anime not found with id: " + Id));
	    animeRepository.delete(anime);
	    return "anime deleted";
	}


	@Override
	public AnimeDbDto getAnimeByName(String animeName) {
		AnimeDb anime = animeRepository.findByAnimeName(animeName);
	    if (anime == null) {
	        throw new EntityNotFoundException("Anime not found with name: " + animeName);
	    }
	    return convertToDto(anime);
	}
	
	AnimeDb convertToEntity(AnimeDbDto animeDbDto) {
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