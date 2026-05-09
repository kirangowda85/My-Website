package com.platform.jobboard.modules.user.service;

import com.platform.jobboard.core.exception.BadRequestException;
import com.platform.jobboard.core.exception.ResourceNotFoundException;
import com.platform.jobboard.core.security.JwtTokenProvider;
import com.platform.jobboard.modules.user.dto.AuthDto.AuthResponse;
import com.platform.jobboard.modules.user.dto.AuthDto.LoginRequest;
import com.platform.jobboard.modules.user.dto.AuthDto.RegisterRequest;
import com.platform.jobboard.modules.user.entity.EmployerProfile;
import com.platform.jobboard.modules.user.entity.User;
import com.platform.jobboard.modules.user.entity.WorkerProfile;
import com.platform.jobboard.modules.user.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final WorkerProfileRepository workerProfileRepository;
    private final EmployerProfileRepository employerProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already in use");
        }
        User user = User.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(User.Role.valueOf(request.getRole().toUpperCase()))
                .status(User.UserStatus.ACTIVE)
                .build();
        userRepository.save(user);
        // create profile placeholder based on role
        if (user.getRole() == User.Role.WORKER) {
            WorkerProfile wp = WorkerProfile.builder()
                    .user(user)
                    .userId(user.getId())
                    .build();
            workerProfileRepository.save(wp);
        } else if (user.getRole() == User.Role.EMPLOYER) {
            EmployerProfile ep = EmployerProfile.builder()
                    .user(user)
                    .userId(user.getId())
                    .build();
            employerProfileRepository.save(ep);
        }
        String token = jwtTokenProvider.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .role(user.getRole().name())
                .email(user.getEmail())
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Invalid credentials");
        }
        String token = jwtTokenProvider.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .role(user.getRole().name())
                .email(user.getEmail())
                .build();
    }
}
