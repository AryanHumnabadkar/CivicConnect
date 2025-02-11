package com.civic.dao;

import com.civic.pojos.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpDao extends JpaRepository<OTP, Long> {
    Optional<OTP> findByEmailAndOtpAndUsedFalse(String email, String otp);
}
