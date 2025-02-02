package com.civic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civic.pojos.Permit;

public interface PermitDao extends JpaRepository<Permit, Long> {

}
