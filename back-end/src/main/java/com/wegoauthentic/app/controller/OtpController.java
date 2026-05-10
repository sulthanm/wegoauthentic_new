package com.wegoauthentic.app.controller;

import com.wegoauthentic.app.dto.otp.OtpRequestDto;
import com.wegoauthentic.app.dto.otp.OtpVerifyDto;
import com.wegoauthentic.app.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestBody OtpRequestDto request) {

        otpService.generateAndSendOtp(request.getEmail());

        return ResponseEntity.ok("OTP sent successfully");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpVerifyDto request) {

        boolean verified = otpService.verifyOtp(
                request.getEmail(),
                request.getOtp()
        );

        if (verified) {
            return ResponseEntity.ok("OTP verified successfully");
        }

        return ResponseEntity.badRequest().body("Invalid OTP");
    }
}
