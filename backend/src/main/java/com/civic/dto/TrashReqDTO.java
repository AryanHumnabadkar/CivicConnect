package com.civic.dto;

import java.time.LocalDate;

import com.civic.pojos.Sector;
import com.civic.pojos.User;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TrashReqDTO {
	
	private long id;

	private String description;
	
	private String user;
	
	private String sector;
	
	@Temporal(TemporalType.DATE)
	private LocalDate requestDate;
	
	@Temporal(TemporalType.DATE)
	private LocalDate serviceDate;


}
