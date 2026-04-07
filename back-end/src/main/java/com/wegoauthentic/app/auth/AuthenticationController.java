package com.wegoauthentic.app.auth;

import com.wegoauthentic.app.model.AuthenticationRequest;
import com.wegoauthentic.app.model.AuthenticationResponse;
import com.wegoauthentic.app.model.OtpRequest;
import com.wegoauthentic.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/public/register")
    public ResponseEntity<String> register(@RequestBody OtpRequest registerRequest){
        //we will take input of phone and send them otp here
        return ResponseEntity.ok("OTP Sent");
    }

    @PostMapping("/user/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest registerRequest){
        // validate otp
        // if user doesn't exist -> create user
        // generate JWT token and return
        AuthenticationResponse response = userService.login(registerRequest);
        return ResponseEntity.ok(response);
    }
}
