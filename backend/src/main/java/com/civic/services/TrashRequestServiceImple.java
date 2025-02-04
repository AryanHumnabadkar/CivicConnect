package com.civic.services;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.civic.custom_exceptions.AuthException;
import com.civic.custom_exceptions.ResourceNotFoundException;
import com.civic.dao.TrashRequestDao;
import com.civic.dao.UserDao;
import com.civic.dto.CreateTrashReqDTO;
import com.civic.pojos.Sector;
import com.civic.pojos.TrashRequest;
import com.civic.pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TrashRequestServiceImple implements TrashRequestService {
	
	//dependecy - TrashRequestDao
	@Autowired
	private TrashRequestDao trashDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public String createTrashRequest(CreateTrashReqDTO trashRequestDto) {
		try {
			//map dto to req
			TrashRequest request = mapper.map(trashRequestDto, TrashRequest.class);
			User user = userDao.findById(trashRequestDto.getUserId()).orElseThrow(() -> new AuthException("Not valid user found!!"));
			request.setUser(user);
			
			Sector sector = user.getAddress().getSector();
			if(sector == null)
				throw new ResourceNotFoundException("User has no sector address registered, please update Address first!!");
			request.setSector(sector);
			
			request.setRequestDate(LocalDate.now());
			
			trashDao.save(request);
			return "Trash req registered!!";
		} catch(RuntimeException e) {
			return e.getMessage();
		}
	}

	@Override
	public TrashRequest getTrashRequestById(Long requestId) {
		 TrashRequest trashRequest = trashDao.findById(requestId)
	                .orElseThrow(() -> new RuntimeException("Trash Request not found"));
		return trashRequest;
	}

	@Override
	public List<TrashRequest> getAllTrashRequests() {
		return trashDao.findAll();
	}

	@Override
	public TrashRequest updateTrashRequest(Long requestId, TrashRequest trashRequestDto) {
		TrashRequest existingTrashRequest = trashDao.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Trash Request not found"));

        existingTrashRequest.setDescription(trashRequestDto.getDescription());
        //set other details when using DTO

        TrashRequest updatedTrashRequest = trashDao.save(existingTrashRequest);
		return updatedTrashRequest;
	}

	@Override
	public String deleteTrashRequest(Long requestId) {
		if(trashDao.existsById(requestId)) {
			trashDao.deleteById(requestId);
			return "Delted successfully";
		}
		return "Req not found";
		
	}
	
	
	

}
