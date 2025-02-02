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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TrashRequest extends BaseEntity{
	
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
