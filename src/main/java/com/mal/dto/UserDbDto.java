package com.mal.dto;

public class UserDbDto {
	private int id;
	private int epsProgress;
	private AnimeDbDto animeDbDto;
	private float userRating;
	private String status;
	
	public UserDbDto() {}

	public UserDbDto(int id, int epsProgress, AnimeDbDto animeDbDto, float userRating, String status) {
		this.id = id;
		this.epsProgress = epsProgress;
		this.animeDbDto = animeDbDto;
		this.userRating = userRating;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEpsProgress() {
		return epsProgress;
	}

	public void setEpsProgress(int epsProgress) {
		this.epsProgress = epsProgress;
	}

	public AnimeDbDto getAnimeDbDto() {
		return animeDbDto;
	}

	public void setAnimeDbDto(AnimeDbDto animeDbDto) {
		this.animeDbDto = animeDbDto;
	}

	public float getUserRating() {
		return userRating;
	}

	public void setUserRating(float userRating) {
		this.userRating = userRating;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
