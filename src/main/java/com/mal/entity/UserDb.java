package com.mal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "myanimelist")
public class UserDb {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)  //fetch only when required(lazy)
	@JoinColumn(name = "anime_id", referencedColumnName = "id",nullable = false)  //userdb has foreign key column named "anime_id" that use referenced column "id" of the AnimeDb entity
	private AnimeDb animeDb;
	
	@Column(name = "eps_progress")
	@Min(value = 0, message = "Progress should not be less than 0")
	private int epsProgress;
	
	@Column(name = "user_rating", precision = 4, scale = 2, nullable = false)
	@DecimalMin(value = "1.0", message = "User rating should not be less than 1")
	@DecimalMax(value = "10.0", message = "User rating should not be greater than 10")
	private float userRating;
	
	@Column(name = "status")
	@Pattern(regexp = "^(cmpl|wtcg|ptw|drpd)$", message = "Invalid status, it should be one of these: cmpl, wtcg, ptw, drpd")
	private String status;
	
	public UserDb() {}
	
	public UserDb(AnimeDb animeDb, float userRating, String status, int epsProgress) {
		super();
		this.animeDb = animeDb;
		this.userRating = userRating;
		this.status = status;
		this.epsProgress = epsProgress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AnimeDb getAnimeDb() {
		return animeDb;
	}

	public void setAnimeDb(AnimeDb animeDb) {
		this.animeDb = animeDb;
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

	public int getEpsProgress() {
		return epsProgress;
	}

	public void setEpsProgress(int epsProgress) {
		this.epsProgress = epsProgress;
	}
}
