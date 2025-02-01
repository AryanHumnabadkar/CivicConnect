package com.civic.services;

import java.util.List;

import com.civic.pojos.User;

public interface CitizenService {
	//
	String updateSelf(User userDetails); //also need to send token 
	String deleteProfile(long userId);
	
}
