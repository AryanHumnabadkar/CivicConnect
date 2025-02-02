package com.civic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civic.pojos.TrashRequest;

public interface TrashRequestDao extends JpaRepository<TrashRequest, Long>{

}
