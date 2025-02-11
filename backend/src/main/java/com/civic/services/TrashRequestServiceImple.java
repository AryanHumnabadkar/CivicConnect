package com.civic.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.civic.custom_exceptions.AuthException;
import com.civic.custom_exceptions.ResourceNotFoundException;
import com.civic.dao.TrashRequestDao;
import com.civic.dao.UserDao;
import com.civic.dto.CreateTrashReqDTO;
import com.civic.dto.TrashReqDTO;
import com.civic.dto.UpdateTrashReqDTO;
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
	public TrashReqDTO getTrashRequestById(Long requestId) {
		 TrashRequest trashRequest = trashDao.findById(requestId)
	                .orElseThrow(() -> new RuntimeException("Trash Request not found"));
		 
		 TrashReqDTO request = mapper.map(trashRequest, TrashReqDTO.class);
		 request.setSector(trashRequest.getSector().getSectorName().name());
		 request.setUser(trashRequest.getUser().getName());
		return request;
	}

	@Override
	public List<TrashReqDTO> getAllTrashRequests() {
	    return trashDao.findAll().stream()
	            .map(request -> {
	                TrashReqDTO reqDTO = mapper.map(request, TrashReqDTO.class);
	                reqDTO.setSector(request.getSector().getSectorName().name());
	                reqDTO.setUser(request.getUser().getName());
	                return reqDTO;
	            })
	            .collect(Collectors.toList());
	}

	@Override
	public TrashReqDTO updateTrashRequest(Long requestId, UpdateTrashReqDTO serviceDate) {
		TrashRequest existingTrashRequest = trashDao.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Trash Request not found"));

        existingTrashRequest.setServiceDate(serviceDate.getServiceDate());

        TrashRequest updatedTrashRequest = trashDao.save(existingTrashRequest);
        
		return mapper.map(updatedTrashRequest, TrashReqDTO.class);
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
