package com.wegoauthentic.app.config.authJwt;

import com.wegoauthentic.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return userid-> userRepository.findById(userid)
                .orElseThrow(()-> new UsernameNotFoundException("User is not Present.Please Sign Up"));
    }
}
