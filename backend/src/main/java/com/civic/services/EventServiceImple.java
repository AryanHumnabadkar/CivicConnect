package com.civic.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.civic.dao.EventDao;
import com.civic.dao.PermitDao;
import com.civic.dao.ReceiptDao;
import com.civic.dao.UserDao;
import com.civic.dto.EventPreRegisterDTO;
import com.civic.pojos.Event;
import com.civic.pojos.Permit;
import com.civic.pojos.PermitStatus;
import com.civic.pojos.Receipt;
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
	
	@Autowired
	private ReceiptDao receiptDao;

	@Override
	public Event preRegisterEvent(EventPreRegisterDTO evntDetails, long userId) {
		try {
			//Here need to do: 1.First check if payment is done. If yes, get the Permit and set
			//for now, create permit in PENDING status. Later do it in Permit
			
			
			//2.Get the User and set
			User user = userDao.findById(userId).orElseThrow(() -> new Exception("User not found!"));
			eventDetails.setUser(user);
			
			//3.save the event
			return eventDao.save(eventDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
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
		//TODO - 
		return null;
	}

	@Override
	public Permit updatePermitStatus(long eventId) {
		//AdminController will use this
		try {
			//1.find eventById
			Event event = eventDao.findById(eventId)
			        .orElseThrow(() -> new Exception("Event not found with ID: " + eventId));
			
			//2.Call permitService's methods acc to situations
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
		    if (permit.getReceipt() == null) {
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
		try {
			Event event = eventDao.findById(eventid).orElseThrow(() -> new Exception("EventNot found"));
			eventDao.delete(event);
			return "Deleted successfully";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		
	}

}
