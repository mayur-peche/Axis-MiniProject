package com.mal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mal.dto.UserDbDto;
import com.mal.service.UserDbService;

@RestController
@RequestMapping("/myanimelist")
public class UserDbController {

    @Autowired
    private UserDbService userService;

    @PostMapping("/userdb")
    public ResponseEntity<UserDbDto> addAnimeUser(@RequestBody UserDbDto userDbDto) {
        UserDbDto result = userService.addAnimeUser(userDbDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/userdb/{id}/rating")
    public ResponseEntity<String> updateUserRatingById(@PathVariable int id, @RequestParam float userRating) {
        String result = userService.updateUserRatingById(id, userRating);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/userdb/{id}/status")
    public ResponseEntity<String> updateStatusById(@PathVariable int id, @RequestParam String status) {
        String result = userService.updateStatusByID(id, status);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/userdb/{id}/progress")
    public ResponseEntity<String> updateEpisodeProgressById(@PathVariable int id, @RequestParam int epsProgress) {
        String result = userService.updateEpisodeProgressById(id, epsProgress);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/userdb")
    public ResponseEntity<List<UserDbDto>> getAllAnime() {
        List<UserDbDto> result = userService.getAllAnime();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/userdb/{status}")
    public ResponseEntity<List<UserDbDto>> getAnimeByStatus(@PathVariable String status) {
        List<UserDbDto> result = userService.getAnimeByStatus(status);
        return ResponseEntity.ok(result);
    }
}

