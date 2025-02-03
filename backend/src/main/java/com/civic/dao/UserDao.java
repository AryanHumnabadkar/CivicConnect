package com.civic.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civic.pojos.User;

public interface UserDao extends JpaRepository<User, Long>{
	User getUserByEmailAndPassword(String email, String password);

	boolean existsByEmail(String email);

	User findByEmail(String email);
}
