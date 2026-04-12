package com.wegoauthentic.app.service;

public interface OtpService {
    public void generateAndSendOtp(String email);
    public boolean verifyOtp(String email, String inputOtp);
}
