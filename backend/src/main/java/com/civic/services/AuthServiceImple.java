package com.civic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.civic.dao.UserDao;
import com.civic.pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthServiceImple implements AuthService {
	//dependecy here - UseraDao
	@Autowired
	private UserDao userDao;

	@Override
	public String registerUser(User userDetails) {
		System.out.println(userDetails);
		//validations: 1.if email already exists
		//2. encode password
		User createdUser = userDao.save(userDetails);
		return createdUser.getName();
	}

	@Override
	public User login(String email, String password) {
		System.out.println(email + " " + password);
		//find by email first
		//and check if passwords match
		User createdUser = userDao.getUserByEmailAndPassword(email, password);
		return createdUser;
	}

	@Override
	public String logout(long id) {
		//invalidate token later. 
		System.out.println(id);
		return "Logged out successfully";
	}

}
