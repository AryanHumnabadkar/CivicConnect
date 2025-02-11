package com.civic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic.dto.ApiResponse;
import com.civic.dto.ForgotPasswordRequest;
import com.civic.dto.LoginReqDTO;
import com.civic.dto.RegisterUserDTO;
import com.civic.dto.ResetPasswordRequest;
import com.civic.dto.VerifyOTPRequest;
import com.civic.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin()
@Validated
public class AuthController {

	// dependency - authService, AuthenticationManager, JwtUtils
	@Autowired
	private AuthService authService;

	// apis

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO userDetails) {
		return ResponseEntity.ok(new ApiResponse(authService.registerUser(userDetails)));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginReqDTO loginData) {
		// TODO: emailOrPassword exception?
		return ResponseEntity.status(200).body(authService.login(loginData.getEmail(), loginData.getPassword())); // ret
																													// jwt
																													// token
																													// here
	}

	@PostMapping("/logout/{userId}")
	public ResponseEntity<?> logout(@PathVariable Long userId) {
		// No server-side invalidation needed. Invalidate on frontend only
		return ResponseEntity.ok(new ApiResponse(authService.logout()));
	}
	
	//Password reset - email
	@PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            authService.sendPasswordResetOTP(request.getEmail());
            return ResponseEntity.ok()
                .body(new ApiResponse("OTP sent successfully to your email"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse("Failed to send OTP: " + e.getMessage()));
        }
    }
	
	@PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOTP(@RequestBody VerifyOTPRequest request) {
        try {
            boolean isValid = authService.verifyOTP(request.getEmail(), request.getOtp());
            if (isValid) {
                return ResponseEntity.ok()
                    .body(new ApiResponse("OTP verified successfully"));
            }
            return ResponseEntity.badRequest()
                .body(new ApiResponse("Invalid OTP"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse("OTP verification failed: " + e.getMessage()));
        }
    }
	
	@PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            authService.resetPassword(
                request.getEmail(), 
                request.getOtp(), 
                request.getNewPassword()
            );
            return ResponseEntity.ok()
                .body(new ApiResponse("Password reset successful"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse("Password reset failed: " + e.getMessage()));
        }
    }

}
