package com.jara.authapi.auth.service;

import com.jara.authapi.auth.dto.*;
import com.jara.authapi.security.JwtService;
import com.jara.authapi.token.model.RefreshToken;
import com.jara.authapi.token.service.RefreshTokenService;
import com.jara.authapi.user.model.Role;
import com.jara.authapi.user.model.User;
import com.jara.authapi.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow();

        String accessToken = jwtService.generateToken(user.getUsername());

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user);

        return new LoginResponse(
                accessToken,
                refreshToken.getToken()
        );
    }

    @Override
    public void register(RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {

        String requestToken = request.getRefreshToken();

        RefreshToken refreshToken = refreshTokenService
                .findByToken(requestToken)
                .map(refreshTokenService::verifyExpiration)
                .orElseThrow(() -> new RuntimeException("Refresh token no válido"));

        String newAccessToken = jwtService.generateToken(
                refreshToken.getUser().getUsername()
        );

        return new RefreshTokenResponse(newAccessToken);
    }
}