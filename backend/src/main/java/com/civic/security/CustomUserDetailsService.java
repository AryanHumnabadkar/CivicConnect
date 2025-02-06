package com.civic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.civic.dao.UserDao;
import com.civic.pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
	//DI here is UserDao 
	
	@Autowired
	private UserDao userDao;

	//override loaduserByUsername
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!"));
		return new CustomUserDetails(user);
	}
	
	
}
