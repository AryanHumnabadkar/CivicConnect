package com.civic.services;

import java.util.List;

import com.civic.dto.SectorDTO;
import com.civic.dto.UpdateUserDTO;
import com.civic.dto.UserDTO;
import com.civic.pojos.User;

public interface CitizenService {
	//
	UserDTO getProfileDetails(long userId);
	String updateProfile(long userId, UpdateUserDTO userDetails); //
	String deleteProfile(long userId);
	List<SectorDTO> getSectors();
	
	//TODO: update password, forgotPassword
	
}
