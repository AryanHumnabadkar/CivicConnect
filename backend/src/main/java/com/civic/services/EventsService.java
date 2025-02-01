package com.civic.services;

import java.util.List;

import com.civic.pojos.Events;
import com.civic.pojos.Permit;
import com.civic.pojos.User;

public interface EventsService {
	Events registerEvent(Events eventDetails, long userId); //need to ret DTO of Event and Permit
	Events getEventById(long id);
	List<Events> getAllEvents();
	User getUserByEvent(long eventId);
	Permit getPermit(long eventId);
	
	
}
