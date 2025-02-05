package com.civic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civic.pojos.Address;

public interface AddressDao extends JpaRepository<Address, Long> {

}
