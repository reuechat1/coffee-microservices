package com.yan.authservice.dtos.gateway;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
