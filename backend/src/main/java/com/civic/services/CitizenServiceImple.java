package com.civic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.civic.dao.UserDao;
import com.civic.pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CitizenServiceImple implements CitizenService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User getProfileDetails(long userId) {
		try {
			//here also get header, check if both userId's are same then only send data of current user only
			if(userDao.existsById(userId))
				return userDao.findById(userId).
						orElseThrow( () -> new Exception("User not found!"));
		}
		catch (Exception e) {
			return null;
		}
		return null;
	}

	@Override
	public String updateProfile(User userDetails) {
		//1. get id and check if user exists
		try {
			User existingUser = userDao.findById(userDetails.getId())
					.orElseThrow(() -> new Exception("User not found!"));
			//if got
			existingUser.setEmail(userDetails.getEmail());
//			existingUser.getAddress(userDetails.getAddress());
			//call setters on that user
			//and save
			userDao.save(existingUser);
			return "updated succcessfullt!";
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}

	@Override
	public String deleteProfile(long userId) {
		// Check if the user exists
        if (!userDao.existsById(userId)) {
            return "User not found";
        }

        // Delete the user
        userDao.deleteById(userId);
		return "deleted successfullt";
	}


}
