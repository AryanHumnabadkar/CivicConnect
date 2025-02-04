package com.civic.services;

import com.civic.dto.ApiResponse;
import com.civic.dto.LoginRespDTO;
import com.civic.dto.RegisterUserDTO;
import com.civic.pojos.User;

public interface AuthService {
	//later ret type and params need to change as per DTO
	String registerUser(RegisterUserDTO userDetails);
	LoginRespDTO login(String email, String password);	 //need to ret jwt token
	String logout(long id); //later param = String token
	
}
