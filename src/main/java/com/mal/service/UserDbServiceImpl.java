package com.mal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mal.dto.AnimeDbDto;
import com.mal.dto.UserDbDto;
import com.mal.entity.UserDb;
import com.mal.exception.CustomConstraintViolationException;
import com.mal.repository.AnimeRepository;
import com.mal.repository.UserRepository;

@Service
public class UserDbServiceImpl implements UserDbService{

	@Autowired
	private UserRepository userrepository;
	
	@Autowired
    private AnimeDbServiceImpl animeDbService;
	
	 @Autowired
	 private AnimeRepository animeRepository;
	 
	 @Override
	    public UserDbDto addAnimeUser(UserDbDto userDbDto) {

	        // Check if AnimeDb record exists
	        AnimeDbDto animeDbDto = animeDbService.getAnimeDto(userDbDto.getAnimeDbDto().getId());
	        if (animeDbDto == null) {
	            throw new DataIntegrityViolationException("Given anime does not exist in our database. Please try another Anime");
	        }

	        // Set the AnimeDbDto object in the UserDbDto
	        userDbDto.setAnimeDbDto(animeDbDto);

	        // Check if user rating is between 1.0 and 10.0
	        if (userDbDto.getUserRating() < 1.0 || userDbDto.getUserRating() > 10.0) {
	            throw new CustomConstraintViolationException("User rating should be between 1.0 and 10.0", null);
	        }

	        // Check if episode progress is negative
	        if (userDbDto.getEpsProgress() < 0) {
	            throw new CustomConstraintViolationException("Episode progress cannot be negative", null);
	        }
	        UserDb userDb = convertToEntity(userDbDto);
	        userDb = userrepository.save(userDb);
	        return convertToDto(userDb);
	    }


	@Override
	public String updateUserRatingById(int id, float userRating) {
	    Optional<UserDb> userDbOptional = userrepository.findById(id);
	    if (userDbOptional.isPresent()) {
	        UserDb userDb = userDbOptional.get();
	        
	        // Check if user rating is between 1.0 and 10.0
	        if (userRating < 1.0 || userRating > 10.0) {
	            throw new CustomConstraintViolationException("User rating should be between 1.0 and 10.0", null);
	        }
	        
	        userDb.setUserRating(userRating);
	        userrepository.save(userDb);
	        UserDbDto userDto = convertToDto(userDb);
	        return ("User rating updated successfully. " + userDto.toString());
	    } else {
	        return ("Cannot find anime with given id.");
	    }
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
	        return "succees";
	    } else {
	        return "cannot find anime with given id";
	    }
	}


	@Override
	public List<UserDbDto> getAnimeByStatus(String status) {
	    List<UserDb> userdb = (List<UserDb>)userrepository.findByStatus(status);
	    if (userdb == null) {
	        throw new EntityNotFoundException("No Anime found with status as :" + status);
	    }
	    List<UserDbDto> userDbDtos = new ArrayList<>();
	    for(UserDb user : userdb) {
	        userDbDtos.add(convertToDto(user));
	    }
	    return userDbDtos;
	}


	@Override
	public List<UserDbDto> getAllAnime() {
		List<UserDb> userdb = (List<UserDb>)userrepository.findAll();
		List<UserDbDto> userDbDtos = new ArrayList<>();
		for(UserDb user : userdb) {
			userDbDtos.add(convertToDto(user));
		}
		return userDbDtos;
	}
	
	private UserDb convertToEntity(UserDbDto userDbDto) {
	    UserDb userDb = new UserDb();
	    userDb.setId(userDbDto.getId());
	    userDb.setAnimeDb(animeDbService.convertToEntity(userDbDto.getAnimeDbDto()));
	    userDb.setStatus(userDbDto.getStatus());
	    userDb.setUserRating(userDbDto.getUserRating());
	    userDb.setEpsProgress(userDbDto.getEpsProgress());
	    return userDb;
	}


	private UserDbDto convertToDto(UserDb userDb){
	    UserDbDto userDbDto = new UserDbDto();
	    userDbDto.setId(userDb.getId());
	    userDbDto.setUserRating(userDb.getUserRating());
	    userDbDto.setEpsProgress(userDb.getEpsProgress());
	    userDbDto.setStatus(userDb.getStatus());
	    return userDbDto;
	}

	
}