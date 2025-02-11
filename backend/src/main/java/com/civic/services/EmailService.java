package com.civic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String to, String otp) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        
        helper.setFrom("noreply@civicconnect.com");
        helper.setTo(to);
        helper.setSubject("Password Reset OTP");

        String htmlContent = String.format("""
            <div style="font-family: Arial, sans-serif; padding: 20px; color: #4A8DAB;">
                <h2>Password Reset Request</h2>
                <p>Your OTP for password reset is: <strong style="color: #F96E2A;">%s</strong></p>
                <p>This OTP will expire in 10 minutes.</p>
                <p>If you didn't request this, please ignore this email.</p>
                <hr>
                <p style="color: #78B3CE;">CivicConnect Team</p>
            </div>
        """, otp);

        helper.setText(htmlContent, true);
        mailSender.send(message);
    }
}