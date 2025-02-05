package com.civic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civic.pojos.Sector;
import com.civic.pojos.SectorValues;

public interface SectorDao extends JpaRepository<Sector, Long> {

	Sector findBySectorName(SectorValues sector);

}
