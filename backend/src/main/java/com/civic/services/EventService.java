package com.civic.services;

import java.util.List;

import com.civic.dto.EventPreRegisterDTO;
import com.civic.pojos.Event;
import com.civic.pojos.User;

public interface EventService {
	Event preRegisterEvent(EventPreRegisterDTO evntDetails, long userId); //need to ret DTO of Event and Permit
	Event getEventById(long eventId);
	List<Event> getAllEvents();
	User getUserByEvent(long eventId);
	
	
	//updates
	String updateEventDetails(long eventId, Event eventUpdates);
	
	
	//deletes
	String deleteEvent(long eventid);

	
	
}
