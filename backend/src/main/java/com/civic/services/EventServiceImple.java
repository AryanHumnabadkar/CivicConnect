package com.civic.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.civic.custom_exceptions.ResourceNotFoundException;
import com.civic.dao.EventDao;
import com.civic.dao.ReceiptDao;
import com.civic.dao.UserDao;
import com.civic.dto.EventPreRegisterDTO;
import com.civic.dto.RegisterEventResponseDTO;
import com.civic.dto.TrashReqDTO;
import com.civic.dto.UpdatePermitRequestDTO;
import com.civic.dto.UpdatePermitResponseDTO;
import com.civic.pojos.Event;
import com.civic.pojos.PermitStatus;
import com.civic.pojos.Receipt;
import com.civic.pojos.Sector;
import com.civic.pojos.User;
import com.razorpay.RazorpayException;

import ch.qos.logback.core.model.Model;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EventServiceImple implements EventService {

	// dependecies - EventDao and UserDao, PermitDao
	@Autowired
	private EventDao eventDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ReceiptDao receiptDao;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PaymentServices paymentServices;

	@Override
	public RegisterEventResponseDTO preRegisterEvent(EventPreRegisterDTO eventDetails, long userId) {
		// set status
		Event event = modelMapper.map(eventDetails, Event.class);
		event.setStatus(PermitStatus.PENDING);
		// get the user using userId
		User user = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
		event.setUser(user);
		// set the sector
		// first check if address available
		if (user.getAddress() == null) {
			throw new ResourceNotFoundException("Address not found for the user!");
		}
		event.setSector(user.getAddress().getSector());

		// save the event
		eventDao.save(event);

		try {
			// create-oider
			String order = paymentServices.createOrder(50);
			RegisterEventResponseDTO response = new RegisterEventResponseDTO();
			response.setEventId(event.getId());
			response.setOrder(order);
			return response;
		} catch (RazorpayException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

	}

	@Override
	public Event getEventById(long eventId) {
		Event event = eventDao.findById(eventId).orElse(null); // need to handle exception properly
		return event;
	}

	@Override
	public List<UpdatePermitResponseDTO> getAllEvents() {
		return eventDao.findAll().stream()
				.map(event -> {
					UpdatePermitResponseDTO eventRes = modelMapper.map(event, UpdatePermitResponseDTO.class);
					eventRes.setUserName(event.getUser().getName());
					eventRes.setSectorName(event.getSector().getSectorName().name());
	                return eventRes;
	            })
				.collect(Collectors.toList());
	}

	@Override
	public User getUserByEvent(long eventId) {
		Event event = eventDao.findById(eventId).orElse(null); // need to handle exception properly
		if (event != null)
			return event.getUser();
		return null;
	}

	@Override
	public UpdatePermitResponseDTO updatePermitStatus(long eventId, UpdatePermitRequestDTO permitDetails) {
		// 1.add receipt to Receipt table

		Receipt receipt = modelMapper.map(permitDetails, Receipt.class);
		receipt.setReceipt_date(LocalDate.now());
		receiptDao.save(receipt);

		// 2. update status and
		Event event = eventDao.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event not found!"));
		event.setStatus(PermitStatus.APPROVED);
		event.setReceipt(receipt);
		eventDao.save(event);

		return modelMapper.map(event, UpdatePermitResponseDTO.class);
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

	@Override
	public String updateEventDetails(long eventId, Event eventUpdates) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'updateEventDetails'");
	}

}
