package com.mal.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mal.entity.AnimeDb;
import com.mal.service.AnimeDbService;

@RestController
@RequestMapping("/anime")
public class AnimeDbController {
	
	@Autowired
	private AnimeDbService animedbService;
	
	@PostMapping("/animedb")
	public ResponseEntity<AnimeDb> addAnime(@Validated @RequestBody AnimeDb animedb) {
		return new ResponseEntity<AnimeDb>(animedbService.addAnime(animedb), HttpStatus.OK);
	}
	
	@GetMapping("/animedb")
	public ResponseEntity<List<AnimeDb>> getAllAnime() {
		return new ResponseEntity<List<AnimeDb>>(animedbService.getAllAnime(), HttpStatus.OK);
	}
	
	@GetMapping("/animedb/name")
	public ResponseEntity<AnimeDb> getAnimeByName(@RequestParam("animeName") String animeName) {
		if (animeName != null) {
			return new ResponseEntity<AnimeDb>(animedbService.getAnimeByName(animeName), HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/animedb/{id}")
	public ResponseEntity<AnimeDb> updateAnimeById(@PathVariable("id") int id, @RequestBody AnimeDb animedb) {
		AnimeDb updatedAnime = animedbService.updateAnimeById(id, animedb);
		if (updatedAnime != null) {
			return new ResponseEntity<AnimeDb>(updatedAnime, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/animedb/{id}")
	public ResponseEntity<Void> deleteAnimeById(@PathVariable("id") int id) {
		animedbService.deleteAnimeById(id);
		return ResponseEntity.noContent().build();
	}
	
}