package com.mal.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
		        
		        // Set the new status value and validate it
		        userDb.setStatus(status);		
		        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();   //creates a ValidatorFactory instance using the default validation provider.
		        Validator validator = factory.getValidator();  //create a Validator instance from the ValidatorFactory instance.
		        
		        
		        Set<ConstraintViolation<UserDb>> violations = validator.validateProperty(userDb, "status");
		        //validates the status property of the UserDb object using the Validator.
		        //any violations stored in a Set of ConstraintViolation objects that takes UserDb type of object. 

		        
		        // If violations set<> is not empty , throw a ConstraintViolationException with the error message from iterator
		        if (!violations.isEmpty()) {
		            String errorMessage = violations.iterator().next().getMessage(); 
		            //.iterator() returns an iterator object to loop through.
		            //.next() returns the next element. but in this case, only first element will be returned
		            //.getMessage() will get the error message returned by constraint violation
 
		            throw new ConstraintViolationException(errorMessage, violations);
		        }
		        
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