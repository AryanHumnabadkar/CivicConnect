package com.civic.pojos;

import jakarta.persistence.*;

@Entity
public class Address extends BaseEntity{
	
	private String city;
	private String street;
	private String state;
	
	@ManyToOne
	private Sector sector;
	

}
