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
		User createdUser = userDao.save(userDetails);
		return createdUser.getName();
	}

	@Override
	public User login(String email, String password) {
		System.out.println(email + " " + password);
		User createdUser = userDao.getUserByEmailAndPassword(email, password);
		return createdUser;
	}

	@Override
	public String logout(long id) {
		// TODO Auto-generated method stub
		System.out.println(id);
		return "loggedout";
	}

}
