package com.zionfellowship.youthcamp.service;

import com.zionfellowship.youthcamp.dto.LoginRequest;
import com.zionfellowship.youthcamp.dto.LoginResponse;
import com.zionfellowship.youthcamp.entity.Admin;
import com.zionfellowship.youthcamp.repository.AdminRepository;
import com.zionfellowship.youthcamp.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository adminRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {

        Admin admin = adminRepository
                .findByUsername(request.username())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid username or password"
                        )
                );

        if (!passwordEncoder.matches(
                request.password(),
                admin.getPassword()
        )) {
            throw new IllegalArgumentException(
                    "Invalid username or password"
            );
        }

        String token = jwtService.generateToken(
                admin.getUsername()
        );

        return new LoginResponse(
                token,
                "Bearer"
        );
    }
}