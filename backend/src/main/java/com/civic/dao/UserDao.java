package com.civic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civic.pojos.User;

public interface UserDao extends JpaRepository<User, Long>{
	
}
