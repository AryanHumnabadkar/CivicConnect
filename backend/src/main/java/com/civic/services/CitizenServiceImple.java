package com.civic.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.civic.custom_exceptions.ResourceNotFoundException;
import com.civic.dao.AddressDao;
import com.civic.dao.SectorDao;
import com.civic.dao.UserDao;
import com.civic.dto.UpdateUserDTO;
import com.civic.dto.UserDTO;
import com.civic.pojos.Address;
import com.civic.pojos.Sector;
import com.civic.pojos.SectorValues;
import com.civic.pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CitizenServiceImple implements CitizenService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SectorDao sectorDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UserDTO getProfileDetails(long userId) {
		//here also get header, check if both userId's are same then only send data of current user only
		User user = userDao.findById(userId).
				orElseThrow(() -> new ResourceNotFoundException("User not found!"));	
		return mapper.map(user, UserDTO.class);
	}

	@Override
	public String updateProfile(long userId, UpdateUserDTO userDetails) {
		//1. get id and check if user exists
		User existingUser = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found!"));
		//if got
		if(!userDetails.getEmail().equals(""))
			existingUser.setEmail(userDetails.getEmail());
		
		if(userDetails.getAddress() != null) {
			//need to set sector and address
			Sector sector = sectorDao.findBySectorName(SectorValues.valueOf(userDetails.getAddress().getSector()));
			if (sector == null) 
				throw new ResourceNotFoundException("Invalid sector!");
			Address address = mapper.map(userDetails.getAddress(), Address.class);
			address.setSector(sector);
			addressDao.save(address);
			existingUser.setAddress(address);
		}
		
		//and save
		userDao.save(existingUser);
		return "updated succcessfullt!";
		
	}

	@Override
	public String deleteProfile(long userId) {
		// Check if the user exists
        if (!userDao.existsById(userId)) {
           throw new ResourceNotFoundException("User not found!");
        }
        // Delete the user
        userDao.deleteById(userId);
		return "deleted successfullt";
	}
	


}
