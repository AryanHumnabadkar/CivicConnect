package com.civic.services;

import java.util.List;

import com.civic.pojos.User;

public interface CitizenService {
	//
	User getProfileDetails(long userId);
	String updateProfile(User userDetails); //
	String deleteProfile(long userId);
	
}
