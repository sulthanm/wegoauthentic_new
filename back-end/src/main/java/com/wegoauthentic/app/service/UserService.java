package com.wegoauthentic.app.service;

import com.wegoauthentic.app.dto.auth.AuthenticationRequest;
import com.wegoauthentic.app.dto.auth.AuthenticationResponse;

public interface UserService {

     AuthenticationResponse login(AuthenticationRequest registerRequest);
}
