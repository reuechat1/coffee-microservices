package com.yan.authservice.dtos.gateway;

import lombok.Data;

@Data
public class RefreshRequest {
    private String refreshToken;
}
