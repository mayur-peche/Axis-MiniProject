package com.mal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "animelist")
public class AnimeDb {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int animeId;
	private String animeName;
	private float globalRatings;
	private int totalEpisodes;
	private int episodeProgress;
	
	@Column(unique = true)
	private int ranking;
	
	@Pattern(regexp = "CMPL|WTCG|DRPD|PTW")
	private String status;
	
	public AnimeDb(String animeName,int animeId, float globalRatings, int totalEpisodes, int episodeProgress, int ranking,
			String status) {
		this.animeName = animeName;
		this.globalRatings = globalRatings;
		this.totalEpisodes = totalEpisodes;
		this.episodeProgress = episodeProgress;
		this.ranking = ranking;
		this.status = status;
		this.animeId = animeId;
		
	}
	public AnimeDb() {
		
	}
	public String getAnimeName() {
		return animeName;
	}
	public void setAnimeName(String animeName) {
		this.animeName = animeName;
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
	public int getEpisodeProgress() {
		return episodeProgress;
	}
	public void setEpisodeProgress(int episodeProgress) {
		this.episodeProgress = episodeProgress;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAnimeId() {
		return animeId;
	}
	public void setAnimeId(int animeId) {
		this.animeId = animeId;
	}
	
	
}
