package com.platform.jobboard.modules.user.controller;

import com.platform.jobboard.core.utils.ApiResponse;
import com.platform.jobboard.modules.user.dto.AuthDto.AuthResponse;
import com.platform.jobboard.modules.user.dto.AuthDto.LoginRequest;
import com.platform.jobboard.modules.user.dto.AuthDto.RegisterRequest;
import com.platform.jobboard.modules.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ApiResponse.success(authService.register(request));
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success(authService.login(request));
    }
}
