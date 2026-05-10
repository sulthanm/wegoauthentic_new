package com.wegoauthentic.app.dto.otp;

import lombok.Data;

@Data
public class OtpVerifyDto {
    private String email;
    private String otp;
}