package com.civic.pojos;

import jakarta.persistence.*;

@Entity
public class Permit extends BaseEntity{
	
	@Enumerated
	@Column(name = "permit_status")
	private PermitStatus status;
	
	@OneToOne
	private Recipt recipt;

}
