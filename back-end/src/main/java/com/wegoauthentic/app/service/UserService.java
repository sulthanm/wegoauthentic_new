package com.wegoauthentic.app.service;

import com.wegoauthentic.app.model.AuthenticationRequest;
import com.wegoauthentic.app.model.AuthenticationResponse;

public interface UserService {

     AuthenticationResponse login(AuthenticationRequest registerRequest);
}
