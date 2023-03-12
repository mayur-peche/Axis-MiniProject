package com.mal.dto;

public class AnimeDbDto {
	private int id;
	 private String animeName;
	 private int ranking;
	 private float globalRatings;
	 private int totalEpisodes;
	 
	public AnimeDbDto() {}

	public AnimeDbDto(int id, String animeName, int ranking, float globalRatings, int totalEpisodes) {
		this.id = id;
		this.animeName = animeName;
		this.ranking = ranking;
		this.globalRatings = globalRatings;
		this.totalEpisodes = totalEpisodes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnimeName() {
		return animeName;
	}

	public void setAnimeName(String animeName) {
		this.animeName = animeName;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public float getGlobalRatings() {
		return globalRatings;
	}

	public void setGlobalRatings(float globalRatings) {
		this.globalRatings = globalRatings;
	}

	public int getTotalEpisodes() {
		return totalEpisodes;
	}

	public void setTotalEpisodes(int totalEpisodes) {
		this.totalEpisodes = totalEpisodes;
	}
	 
	
}
