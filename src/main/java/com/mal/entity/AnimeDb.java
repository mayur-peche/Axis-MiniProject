package com.mal.entity;

import javax.validation.constraints.*;
import javax.persistence.*;

@Entity
@Table(name = "animelist")
public class AnimeDb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank (message = "Anime name cannot be empty")
    @Column(name = "anime_name", nullable = false)
    private String animeName;

    @NotNull(message = "Ranking cannot be null")
    @Column(name = "ranking", nullable = false, unique = true)
    private int ranking;

    @NotNull(message = "MAL rating cannot be null")
    @DecimalMin(value = "1.0", message = "MAL rating should not be less than 1.0")
    @DecimalMax(value = "10.0", message = "MAL rating should not be greater than 10.0")
    @Column(name = "mal_rating", precision = 4, scale = 2, nullable = false) //precision means total digit in decimal including both whole and decimal.scale means no. of digit allowed after .
    private float globalRatings;


    @NotNull(message = "Total episodes cannot be null")
    @Positive(message = "Total episodes should be a positive number")
    @Column(name = "total_episodes", nullable = false)
    private int totalEpisodes;

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

    public float getGlobalRatings() {
        return globalRatings;
    }

    public void setGlobalRatings(float globalRatings) {
        this.globalRatings = globalRatings;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

}

