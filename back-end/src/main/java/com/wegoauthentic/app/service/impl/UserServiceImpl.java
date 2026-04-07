package com.wegoauthentic.app.service.impl;

import com.wegoauthentic.app.config.JwtService;
import com.wegoauthentic.app.entity.User;
import com.wegoauthentic.app.enums.Role;
import com.wegoauthentic.app.model.AuthenticationRequest;
import com.wegoauthentic.app.model.AuthenticationResponse;
import com.wegoauthentic.app.repository.UserRepository;
import com.wegoauthentic.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;



    @Override
    public AuthenticationResponse login(AuthenticationRequest registerRequest) {
        boolean isValidUser = validateOtp(registerRequest);

        if(!isValidUser){
            throw new RuntimeException("Invalid OTP");
        }

        User user = userRepository.findByPhone(registerRequest.getPhone())
                .orElseGet(
                        ()->{
                            User newUser = new User();
                            newUser.setPhone(registerRequest.getPhone());
                            newUser.setRole(Role.USER);
                            return userRepository.save(newUser);
                        }
                );

        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private boolean validateOtp(AuthenticationRequest registerRequest){
        //here we needs to fetch otp from redis and validate it
        return true;
    }
}
