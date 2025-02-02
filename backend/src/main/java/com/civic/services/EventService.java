package com.civic.services;

import java.util.List;

import com.civic.pojos.Event;
import com.civic.pojos.Permit;
import com.civic.pojos.User;

public interface EventService {
	Event registerEvent(Event eventDetails, long userId); //need to ret DTO of Event and Permit
	Event getEventById(long eventId);
	List<Event> getAllEvents();
	User getUserByEvent(long eventId);
	Permit getPermit(long eventId);
	
	//updates
	String updateEventDetails(long eventId, Event eventUpdates);
	Permit updatePermitStatus(long eventId);
	
	//deletes
	String deleteEvent(long eventid);
	
	
}
