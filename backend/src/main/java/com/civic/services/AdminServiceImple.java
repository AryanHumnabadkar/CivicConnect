package com.civic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.civic.dao.UserDao;
import com.civic.pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminServiceImple implements AdminService {
	
	//dependency - UserDao
	@Autowired UserDao userDao;
	
	@Override
	public User getUserById(long userId) {
		try {
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
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public String updateProfile(User adminDetails) {
		//1. get id and check if user exists
				try {
					User existingUser = userDao.findById(adminDetails.getId())
							.orElseThrow(() -> new Exception("User not found!"));
					//if got
					existingUser.setEmail(adminDetails.getEmail());
//					existingUser.getAddress(userDetails.getAddress());
					//call setters on that user
					//and save
					userDao.save(existingUser);
					return "updated succcessfully!";
				} catch (Exception e) {
					return e.getMessage();
				}
	}

	@Override
	public String updateCitizenProfile(long citizenId, User citizenDetails) {
		//here also get token from controller and check if the user is admin or not // or can do this in controller itself
		//1. get id and check if user exists
				try {
					User existingUser = userDao.findById(citizenId)
							.orElseThrow(() -> new Exception("User not found!"));
					//if got
					existingUser.setEmail(citizenDetails.getEmail());
//					existingUser.getAddress(userDetails.getAddress());
					//call setters on that user
					//and save
					userDao.save(existingUser);
					return "updated citizen succcessfully!";
				} catch (Exception e) {
					return e.getMessage();
				}
	}
	
	//

	@Override
	public String deleteProfile(long adminId) {
		if (!userDao.existsById(adminId)) {
            return "User not found";
        }

        // Delete the user
        userDao.deleteById(adminId);
		return "deleted successfullt";
	}

	@Override
	public String deleteCitizenProfile(long citizenId) {
		if (!userDao.existsById(citizenId)) {
            return "User not found";
        }

        // Delete the user
        userDao.deleteById(citizenId);
		return "deleted successfullt";
	}

	

}
