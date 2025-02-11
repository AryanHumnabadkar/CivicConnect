package com.civic.services;

import java.time.LocalDateTime;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.civic.custom_exceptions.AuthException;
import com.civic.custom_exceptions.ResourceNotFoundException;
import com.civic.dao.OtpDao;
import com.civic.dao.UserDao;
import com.civic.dto.LoginRespDTO;
import com.civic.dto.RegisterUserDTO;
import com.civic.pojos.OTP;
import com.civic.pojos.User;
import com.civic.security.JwtUtils;

import jakarta.mail.MessagingException;
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
	
	//emails
	@Autowired 
	private EmailService emailService;
	
	@Autowired
	private OtpDao otpDao;

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
		LoginRespDTO response = new LoginRespDTO(user.getId(), jwtUtils.generateToken(verifiedAuth), user.getRole().name());
		return response;

	}

	@Override
	public String logout() {
		// No server-side invalidation needed. Already set the token for short time
		return "Logged out successfully";
	}
	
	//password reset stuff
	public void sendPasswordResetOTP(String email) {
        User user = userDao.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String otp = generateOTP();
        OTP otpEntity = new OTP();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(10));
        otpEntity.setUsed(false);
        otpDao.save(otpEntity);
        System.out.println("saved otp!");
        try {
        	System.out.println("sending email");
			emailService.sendPasswordResetEmail(email, otp);
		} catch (MessagingException e) {
			throw new AuthException(e.getMessage());
		}
    }

    public boolean verifyOTP(String email, String otp) {
        OTP otpEntity = otpDao.findByEmailAndOtpAndUsedFalse(email, otp)
            .orElseThrow(() -> new ResourceNotFoundException("Invalid OTP"));

        if (otpEntity.isExpired()) {
            throw new AuthException("OTP has expired");
        }

        return true;
    }
    
    public void resetPassword(String email, String otp, String newPassword) {
        OTP otpEntity = otpDao.findByEmailAndOtpAndUsedFalse(email, otp)
            .orElseThrow(() -> new ResourceNotFoundException("Invalid OTP"));

        if (otpEntity.isExpired()) {
            throw new AuthException("OTP has expired");
        }

        User user = userDao.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setPassword(encoder.encode(newPassword));
        otpDao.save(otpEntity);

        otpEntity.setUsed(true);
        userDao.save(user);
    }

    private String generateOTP() {
        return String.format("%06d", new Random().nextInt(999999));
    }
	
	

}
