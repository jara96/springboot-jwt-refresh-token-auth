package com.jara.authapi.auth.service;

import com.jara.authapi.auth.dto.*;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    void register(RegisterRequest request);

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);

}