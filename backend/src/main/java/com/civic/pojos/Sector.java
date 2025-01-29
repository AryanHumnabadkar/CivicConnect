package com.civic.pojos;

import jakarta.persistence.*;

@Entity
public class Sector {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String SectorName;

}
