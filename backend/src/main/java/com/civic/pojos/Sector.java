package com.civic.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "sector")
public class Sector extends BaseEntity {
	
	@Column(name = "sector_name")
	private SectorValues sectorName;

}
