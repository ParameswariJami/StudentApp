package com.service;

import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    public String generateOTP() {
        int otpLength = 6;
        StringBuilder otp = new StringBuilder(otpLength);
        Random random = new Random();

        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10));  // Random digit between 0 and 9
        }

        return otp.toString();  // Return generated OTP
    }

    public void sendOTP(String emailId, String otp) {
        // Logic for sending OTP via email or SMS
        // You can use the EmailService to send the OTP
    }
}
