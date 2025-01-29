package com.civic.pojos;

import jakarta.persistence.*;


@MappedSuperclass
public class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	

}
