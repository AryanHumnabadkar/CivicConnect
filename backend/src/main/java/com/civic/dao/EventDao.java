package com.civic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civic.pojos.Events;

public interface EventDao extends JpaRepository<Events, Long> {

}
