package com.civic.pojos;

import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
public class Recipt extends BaseEntity {
	
	@Temporal(TemporalType.DATE)
	@Column(name = "receipt_date")
	private LocalDate reciptDate;
	
	

}
