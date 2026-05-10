package com.wegoauthentic.app.repository;

import com.wegoauthentic.app.model.otp.OtpData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class OtpRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // Create Redis key
    private String getKey(String email) {
        return "otp:" + email;
    }

    // Save OTP with TTL
    public void saveOtp(String email, String otp) {
        String key = getKey(email);
        OtpData otpData = new OtpData(otp, 0);
        redisTemplate.opsForValue().set(key, otpData, 5, TimeUnit.MINUTES);
    }

    // Get OTP
    public OtpData getOtpData(String email) {
        String key = getKey(email);
        Object value = redisTemplate.opsForValue().get(key);
        return value != null ? (OtpData) value : null;
    }

    // Delete OTP
    public void deleteOtp(String email) {
        redisTemplate.delete(getKey(email));
    }

    // Optional: check if OTP exists
    public boolean exists(String email) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(getKey(email)));
    }

    public void updateAttempts(String email, OtpData otpData) {
        String key = getKey(email);

        Long ttl = redisTemplate.getExpire(key, TimeUnit.SECONDS);

        redisTemplate.opsForValue().set(key, otpData, ttl, TimeUnit.SECONDS);
    }
}