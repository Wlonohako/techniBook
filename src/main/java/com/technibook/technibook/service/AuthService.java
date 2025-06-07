package com.technibook.technibook.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.technibook.technibook.api.AuthResponse;
import com.technibook.technibook.api.LoginRequest;
import com.technibook.technibook.api.RegisterUserRequest;
import com.technibook.technibook.model.User;
import com.technibook.technibook.repository.UserRepository;
import com.technibook.technibook.util.JwtUtils;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public RegisterUserRequest registerUser(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .activated(true)
                .name(request.getName())
                .surname(request.getSurname())
                .birthDate(request.getBirthDate())
                .sex(request.getSex())
                .build();

        userRepository.save(user);

        return request;

    }

    public AuthResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String token = jwtUtils.generateToken(Map.of("id", user.getId()));

        return new AuthResponse(token);
    }
}
