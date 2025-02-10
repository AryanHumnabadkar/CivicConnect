package com.civic.services;

import java.util.List;

import com.civic.dto.UpdatePermitResponseDTO;
import com.civic.dto.EventPreRegisterDTO;
import com.civic.dto.RegisterEventResponseDTO;
import com.civic.dto.UpdatePermitRequestDTO;
import com.civic.pojos.Event;
import com.civic.pojos.User;

public interface EventService {
	RegisterEventResponseDTO preRegisterEvent(EventPreRegisterDTO evntDetails, long userId); // need to ret DTO of Event
																								// and Permit

	Event getEventById(long eventId);

	List<UpdatePermitResponseDTO> getAllEvents();

	User getUserByEvent(long eventId);

	UpdatePermitResponseDTO updatePermitStatus(long eventId, UpdatePermitRequestDTO permitDetails);

	// updates
	String updateEventDetails(long eventId, Event eventUpdates);

	// deletes
	String deleteEvent(long eventid);

}
