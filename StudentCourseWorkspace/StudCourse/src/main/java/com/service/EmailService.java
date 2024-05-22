package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String emailId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailId);
        message.setSubject("Email Confirmation");
        message.setText("Thank you for registering. Please confirm your email.");
        
        try {
            mailSender.send(message);  // Send email
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
            // Log the exception or notify the developer
        }
    }
}