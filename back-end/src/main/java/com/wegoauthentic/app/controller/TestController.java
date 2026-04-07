package com.wegoauthentic.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api")
    public ResponseEntity<String> testingApi(){
        return ResponseEntity.ok("Successfully reached the controller");
    }
}
