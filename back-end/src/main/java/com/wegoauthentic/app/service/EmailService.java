package com.wegoauthentic.app.service;

public interface EmailService {
    void sendOtpToMail(String toEmail, String otp);
}