package com.civic.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.civic.custom_exceptions.AuthException;
import com.civic.dao.UserDao;
import com.civic.dto.LoginRespDTO;
import com.civic.dto.RegisterUserDTO;
import com.civic.pojos.User;
import com.civic.security.JwtUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthServiceImple implements AuthService {
	// dependecy here - UseraDao, modelmapper

	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AuthenticationManager authManager;

	// JwtUtils
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public String registerUser(RegisterUserDTO userDetails) {
		System.out.println(userDetails);
		// validations: 1.if email already exists
		if (userDao.existsByEmail(userDetails.getEmail())) {
			throw new AuthException("Email already exists!!");
		}
		// 2.check if pass match - this could also be done in frontend only
		if (!userDetails.getPassword().equals(userDetails.getConfirmPassword()))
			throw new AuthException("Passwords do not match!");
		User createdUser = mapper.map(userDetails, User.class);

		// 3. encode password and save
		createdUser.setPassword(encoder.encode(createdUser.getPassword()));// pwd : encrypted using SHA
		userDao.save(createdUser);
		return "User created " + createdUser.getName();

	}

	@Override
	public LoginRespDTO login(String email, String password) {
		// find by email first
		User user = userDao.findByEmail(email).orElseThrow(() -> new AuthException("Wrong Email!! "));

		Authentication verifiedAuth = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, password));

		// create token
		LoginRespDTO response = new LoginRespDTO(user.getId(), jwtUtils.generateToken(verifiedAuth));
		return response;

	}

	@Override
<<<<<<< Updated upstream

	public String logout(long id) {
		// No server-side invalidation needed. Already set the token for short time
		return "Logged out successfully";
	}

}
