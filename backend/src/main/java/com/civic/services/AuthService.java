package com.civic.services;

import com.civic.dto.ApiResponse;
import com.civic.dto.LoginRespDTO;
import com.civic.dto.RegisterUserDTO;
import com.civic.pojos.User;

public interface AuthService {
	String registerUser(RegisterUserDTO userDetails);
	LoginRespDTO login(String email, String password);	 //need to ret jwt token
	String logout(); //later param = String token
	void sendPasswordResetOTP(String email);
	boolean verifyOTP(String email, String otp);
	void resetPassword(String email, String otp, String newPassword);
	
}
