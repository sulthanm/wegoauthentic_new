package com.wegoauthentic.app.service.impl;

import com.wegoauthentic.app.model.otp.OtpData;
import com.wegoauthentic.app.repository.OtpRepository;
import com.wegoauthentic.app.service.EmailService;
import com.wegoauthentic.app.service.OtpService;
import com.wegoauthentic.app.util.OtpGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OtpServiceImpl implements OtpService {

    private final EmailService emailService;
    private final OtpRepository otpRepository;

    @Override
    public void generateAndSendOtp(String email) {
        String otp = OtpGenerator.generateOtp();
        otpRepository.saveOtp(email, otp);
        emailService.sendOtpToMail(email, otp);
    }

    public boolean verifyOtp(String email, String inputOtp) {

        OtpData otpData = otpRepository.getOtpData(email);

        // 1. OTP not found (expired or not generated)
        if (otpData == null) {
            throw new RuntimeException("OTP expired or not found");
        }

        // 2. Check attempts
        if (otpData.getAttempts() >= 5) {
            throw new RuntimeException("Too many attempts. Try again later.");
        }

        // 3. Check OTP match
        if (!otpData.getOtp().equals(inputOtp)) {

            // increment attempts
            otpData.setAttempts(otpData.getAttempts() + 1);
            otpRepository.updateAttempts(email, otpData);

            throw new RuntimeException("Invalid OTP");
        }

        // 4. Success → delete OTP
        otpRepository.deleteOtp(email);

        return true;
    }

}
