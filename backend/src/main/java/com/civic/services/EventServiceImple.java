package com.civic.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.civic.dao.EventDao;
import com.civic.dao.PermitDao;
import com.civic.dao.UserDao;
import com.civic.pojos.Event;
import com.civic.pojos.Permit;
import com.civic.pojos.PermitStatus;
import com.civic.pojos.Sector;
import com.civic.pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EventServiceImple implements EventService {
	
	//dependecies - EventDao and UserDao, PermitDao
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PermitDao permitDao;

	@Override
	public Event registerEvent(Event eventDetails, long userId) {
		//fetch user how is registering event
		try {
			User user = userDao.findById(userId).orElseThrow(() -> new Exception("User not found!"));
			eventDetails.setUser(user);
			//create permit in PENDING status
			Permit permit  = new Permit();
			permit.setStatus(PermitStatus.PENDING);
			permitDao.save(permit);
			eventDetails.setPermit(permit);
			return eventDao.save(eventDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public Event getEventById(long eventId) {
		Event event  = eventDao.findById(eventId).orElse(null); //need to handle exception properly
		return event;
	}

	@Override
	public List<Event> getAllEvents() {
		return eventDao.findAll();
	}

	@Override
	public User getUserByEvent(long eventId) {
		Event event  = eventDao.findById(eventId).orElse(null); //need to handle exception properly
		if(event != null)
			return event.getUser();
		return null;
	}

	@Override
	public Permit getPermit(long eventId) {
		Event event  = eventDao.findById(eventId).orElse(null); //need to handle exception properly
		if(event != null)
			return event.getPermit();
		return null;
	}

	@Override
	public String updateEventDetails(long eventId, Event eventUpdates) {
		
		return null;
	}

	@Override
	public Permit updatePermitStatus(long eventId) {
		//admin controller will sue this
		//find eventById
		try {
			Event event = eventDao.findById(eventId)
			        .orElseThrow(() -> new Exception("Event not found with ID: " + eventId));
			//validations to change permitStatus : PENDING to APPROVED and PENDING to DENIED
			Permit permit = event.getPermit();
		    if (permit == null) {
		        throw new RuntimeException("No permit found for this event.");
		    }
		    if (permit.getStatus() != PermitStatus.PENDING) {
		        throw new RuntimeException("Permit status can only be updated from PENDING.");
		    }
			//do something ??? like check if its date and sector clashes with any other event
		    LocalDate eventDate = event.getDate();
		    Sector eventSector = event.getSector();
		    boolean hasClash = eventDao.existsByDateAndSector(eventDate, eventSector);
		    if (hasClash) {
		        throw new RuntimeException("Another event is already scheduled for the same date and sector.");
		    }
		    
		    //check if payment done
		    if (permit.getRecipt() == null) {
		        throw new RuntimeException("Payment (receipt) is required to approve the permit.");
		    }
		    //change Permit Status
		    //Update the permit status
		    permit.setStatus(PermitStatus.APPROVED);
		    permitDao.save(permit); // Save the updated permit

		    return permit;
			//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public String deleteEvent(long eventid) {
		Event event;
		try {
			event = eventDao.findById(eventid).orElseThrow(() -> new Exception("EventNot found"));
			eventDao.delete(event);
			return "Deleted successfully";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		
	}

}
