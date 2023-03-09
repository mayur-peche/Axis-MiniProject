package com.mal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mal.entity.AnimeDb;
import com.mal.entity.UserDb;
import com.mal.repository.AnimeRepository;
import com.mal.repository.UserRepository;

@Service
public class UserDbServiceImpl implements UserDbService{

	@Autowired
	private UserRepository userrepository;
	
	 @Autowired
	 private AnimeRepository animeRepository;
	 
	@Override
	public UserDb addAnimeUser(UserDb userdb) {
		
		// Check if AnimeDb record exists
        if (!animeRepository.existsById(userdb.getAnimeDb().getId())) {
            throw new DataIntegrityViolationException("Given anime does not exist in our database. Please try another Anime");
        }
        // Check if user rating is between 1.0 and 10.0
        if (userdb.getUserRating() < 1.0 || userdb.getUserRating() > 10.0) {
            throw new IllegalArgumentException("User rating should be between 1.0 and 10.0");
        }

        // Check if episode progress is negative
        if (userdb.getEpsProgress() < 0 ) {
            throw new IllegalArgumentException("Episode progress cannot be negative");
        }
		return userrepository.save(userdb);
	}

	@Override
	public String updateUserRatingById(int id, float userRating) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public String updateStatusByID(int id, String status) {
		 Optional<UserDb> userDbOptional = userrepository.findById(id);
		 if (userDbOptional.isPresent()) {
		        UserDb userDb = userDbOptional.get();
		        userDb.setStatus(status);
		        userrepository.save(userDb);
		        return "Status updated to :"+ status;
		    } else {
		    	return "cannot find anime with given id";
		    }
		
	}

	@Override
	public String updateEpisodeProgressById(int id, int epsProgress) {
	    Optional<UserDb> userDbOptional = userrepository.findById(id);
	    if (userDbOptional.isPresent()) {
	        UserDb userDb = userDbOptional.get();
	        userDb.setEpsProgress(epsProgress);
	        userrepository.save(userDb);
	        return "sucees";
	    } else {
	        return "cannot find anime with given id";
	    }
	}


	@Override
	public UserDb getAnimeByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDb> getAllAnime() {
		// TODO Auto-generated method stub
		return null;
	}

}