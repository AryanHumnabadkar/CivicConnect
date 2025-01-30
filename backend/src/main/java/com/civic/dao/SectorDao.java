package com.civic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civic.pojos.Sector;

public interface SectorDao extends JpaRepository<Sector, Long> {

}
