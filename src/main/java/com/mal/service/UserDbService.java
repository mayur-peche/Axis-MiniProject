package com.mal.service;

import java.util.List;
import com.mal.dto.UserDbDto;


public interface UserDbService {
	 // Add user info for the anime
	UserDbDto addAnimeUser(UserDbDto userdbDto);

	//list all the animes in UserDb
	List<UserDbDto> getAllAnime();

	List<UserDbDto> getAnimeByStatus(String status);

	 // update user rating for an anime
	public String updateUserRatingById(int animeId, float userRating);

	//  update status for an anime
	public String updateStatusByID(int animeId, String status);

	//  update episode progress for an anime
	public String updateEpisodeProgressById(int animeId, int epsProgress);


}
