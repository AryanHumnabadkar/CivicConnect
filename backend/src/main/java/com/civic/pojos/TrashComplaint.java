package com.civic.pojos;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
public class TrashComplaint extends BaseEntity{
	
	private String description;
	
	@Temporal(TemporalType.DATE)
	private LocalDate requestDate;
	@Temporal(TemporalType.DATE)
	private LocalDate serviceDate;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Sector sector;
	

}
