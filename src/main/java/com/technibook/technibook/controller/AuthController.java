package com.technibook.technibook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technibook.technibook.api.AuthResponse;
import com.technibook.technibook.api.LoginRequest;
import com.technibook.technibook.api.RegisterUserRequest;
import com.technibook.technibook.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserRequest> register(@RequestBody RegisterUserRequest request) {
        RegisterUserRequest response = authService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.loginUser(request);
        return ResponseEntity.ok(response);
    }
}
