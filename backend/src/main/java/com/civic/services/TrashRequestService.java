package com.civic.services;

import java.util.List;

import com.civic.pojos.TrashRequest;

public interface TrashRequestService {
	
	TrashRequest createTrashRequest(TrashRequest trashRequestDto);
	TrashRequest getTrashRequestById(Long requestId);
    List<TrashRequest> getAllTrashRequests();
    TrashRequest updateTrashRequest(Long requestId, TrashRequest trashRequestDto);
    String deleteTrashRequest(Long requestId);
	

}
