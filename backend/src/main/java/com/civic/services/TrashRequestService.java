package com.civic.services;

import java.util.List;

import com.civic.dto.CreateTrashReqDTO;
import com.civic.dto.TrashReqDTO;
import com.civic.pojos.TrashRequest;

public interface TrashRequestService {
	
	String createTrashRequest(CreateTrashReqDTO trashRequestDto);
	TrashReqDTO getTrashRequestById(Long requestId);
    List<TrashReqDTO> getAllTrashRequests();
    TrashReqDTO updateTrashRequest(Long requestId, CreateTrashReqDTO trashRequestDto); //need another dto
    String deleteTrashRequest(Long requestId);
	

}
