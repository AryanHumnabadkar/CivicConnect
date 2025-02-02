package com.civic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.civic.dao.TrashRequestDao;
import com.civic.pojos.TrashRequest;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TrashRequestServiceImple implements TrashRequestService {
	
	//dependecy - TrashRequestDao
	@Autowired
	private TrashRequestDao trashDao;

	@Override
	public TrashRequest createTrashRequest(TrashRequest trashRequestDto) {
		//map dto to req
		return trashDao.save(trashRequestDto);
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
