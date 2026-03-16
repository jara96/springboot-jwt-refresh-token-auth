package com.jara.authapi.auth.dto;

import lombok.Data;

@Data
public class RefreshTokenRequest {

    private String refreshToken;

}