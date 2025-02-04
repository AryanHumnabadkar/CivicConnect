package com.civic.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.civic.custom_exceptions.AuthException;
import com.civic.dao.UserDao;
import com.civic.dto.ApiResponse;
import com.civic.dto.LoginRespDTO;
import com.civic.dto.RegisterUserDTO;
import com.civic.pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthServiceImple implements AuthService {
	//dependecy here - UseraDao, modelmapper
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public String registerUser(RegisterUserDTO userDetails) {
		System.out.println(userDetails);
		try {
			//validations: 1.if email already exists
			if (userDao.existsByEmail(userDetails.getEmail())) {
				throw new AuthException("Email already exists!!");
			}
			//2.check if pass match - this could also be done in frontend only
			if(!userDetails.getPassword().equals(userDetails.getConfirmPassword()))
				throw new AuthException("Passwords do not match!");
			//3. encode password
			//TODO
			User createdUser = mapper.map(userDetails, User.class);
			userDao.save(createdUser);
			return "User created " + createdUser.getName();
		} catch (RuntimeException e) {
			return e.getMessage();
		}
		
	}

	@Override
	public LoginRespDTO login(String email, String password) {
		System.out.println(email + " " + password);
		try {
			//find by email first
			User user = userDao.findByEmail(email);
			if(user == null)
				throw new AuthException("No user with email "+ email);
			//and check if passwords match by decoding
			System.out.println(user.getPassword() + "- -" + password);
			if(!user.getPassword().equals(password))
				throw new AuthException("Wrong password!");
			//create token
			
//			User createdUser = userDao.getUserByEmailAndPassword(email, password);
			System.out.println("----------"+user.getId());
			LoginRespDTO loginResponse = mapper.map(user, LoginRespDTO.class);
//			loginResponse.setToken("abc123");
			loginResponse.setMessage("LoggedInSuccessfully");
			return loginResponse;
		} catch (RuntimeException e) {
			// TODO: handle exception
			return new LoginRespDTO(e.getMessage());
		}
		
	}

	@Override
	public String logout(long id) {
		//invalidate token later. 
		System.out.println(id);
		return "Logged out successfully";
	}

}
