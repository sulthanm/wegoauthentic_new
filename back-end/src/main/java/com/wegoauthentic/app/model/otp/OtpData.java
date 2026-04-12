package com.wegoauthentic.app.model.otp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OtpData{
    private String otp;
    private int attempts;
}