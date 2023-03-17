package com.mal.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mal.dto.AnimeDbDto;
import com.mal.service.AnimeDbService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/anime")
public class AnimeDbController {
	
	@Autowired
	private AnimeDbService animedbService;
	
	@PostMapping("/animedb")
	public ResponseEntity<AnimeDbDto> addAnime(@Validated @RequestBody AnimeDbDto animeDbDto) {
		return new ResponseEntity<AnimeDbDto>(animedbService.addAnime(animeDbDto), HttpStatus.OK);
	}
	
	@GetMapping("/animedb")
	public ResponseEntity<List<AnimeDbDto>> getAllAnime() {
		return new ResponseEntity<List<AnimeDbDto>>(animedbService.getAllAnime(), HttpStatus.OK);
	}
	
	@GetMapping("/animedb/name")
	public ResponseEntity<AnimeDbDto> getAnimeByName(@RequestBody AnimeDbDto animeDbDto) {
		String animeName = animeDbDto.getAnimeName();
		if (animeName != null) {
			return new ResponseEntity<AnimeDbDto>(animedbService.getAnimeByName(animeName), HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/animedb/{id}")
	public ResponseEntity<AnimeDbDto> getAnimeById(@PathVariable int id) {
	    AnimeDbDto anime = animedbService.getAnimeById(id);
	    if (anime != null) {
	        return new ResponseEntity<AnimeDbDto>(anime, HttpStatus.OK);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	
	@PutMapping("/animedb/{id}")
	public ResponseEntity<AnimeDbDto> updateAnimeById(@PathVariable("id") int id, @RequestBody AnimeDbDto animeDbDto) {
		AnimeDbDto updatedAnime = animedbService.updateAnimeById(id, animeDbDto);
		if (updatedAnime != null) {
			return new ResponseEntity<AnimeDbDto>(updatedAnime, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/animedb/{id}")
	public ResponseEntity<String> deleteAnimeById(@PathVariable int id) {
		return new ResponseEntity<String>(animedbService.deleteAnimeById(id), HttpStatus.OK);
	}
	
}