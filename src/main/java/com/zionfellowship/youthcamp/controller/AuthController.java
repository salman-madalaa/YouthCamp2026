package com.zionfellowship.youthcamp.controller;

import com.zionfellowship.youthcamp.dto.LoginRequest;
import com.zionfellowship.youthcamp.dto.LoginResponse;
import com.zionfellowship.youthcamp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }
}