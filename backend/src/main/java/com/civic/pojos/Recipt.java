package com.civic.pojos;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
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
public class Recipt extends BaseEntity {
	
	@Temporal(TemporalType.DATE)
	@Column(name = "receipt_date")
	private LocalDate reciptDate;
	
	

}
