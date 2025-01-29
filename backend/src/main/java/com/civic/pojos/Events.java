package com.civic.pojos;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
public class Events extends BaseEntity {
	
	private String name;
	private String description;
	@Temporal(TemporalType.DATE)
	private LocalDate date;
	@Temporal(TemporalType.TIME)
	private LocalTime time;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Sector sector;
	
	@OneToOne
	private Permit permit;
	

}
