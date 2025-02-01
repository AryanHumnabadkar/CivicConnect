package com.civic.services;

import com.civic.pojos.User;

public interface AuthService {
	//later ret type and params need to change as per DTO
	String registerUser(User userDetails);
	User login(String email, String password);	 //need to ret jwt token
	String logout(long id); //later param = String token
	
}
