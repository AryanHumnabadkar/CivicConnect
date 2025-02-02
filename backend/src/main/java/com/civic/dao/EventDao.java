package com.civic.dao;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civic.pojos.Event;
import com.civic.pojos.Sector;

public interface EventDao extends JpaRepository<Event, Long> {
	boolean existsByDateAndSector(LocalDate date, Sector A);
}
