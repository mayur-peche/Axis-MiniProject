package com.mal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mal.entity.AnimeDb;
import com.mal.service.AnimeDbService;

@RestController
@RequestMapping("/myanimelist")
public class AnimeDbController {
	
	@Autowired
	AnimeDbService animedbService;
	
	@PostMapping("/animedb")
	ResponseEntity<AnimeDb> addAnimeDb(@Valid @RequestBody AnimeDb animedb )
	{
		return new ResponseEntity<AnimeDb>(animedbService.addAnime(animedb), null);
	}
}
