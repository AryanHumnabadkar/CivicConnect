package com.civic.services;

import java.util.List;

import com.civic.pojos.User;

public interface AdminService {
	User getUserById(long citizenId);
	List<User> getAllUsers();
	String updateSelf(User adminDetails);
	User updateCitizen(long citizenId, User userDetails);
	String deleteProfile(long adminId);
	String deleteCitizenById(long citizenId);
}
