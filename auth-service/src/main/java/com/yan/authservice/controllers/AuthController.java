package com.yan.authservice.controllers;

import com.yan.authservice.dtos.gateway.AuthRequest;
import com.yan.authservice.services.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        log.info("Авторизация");
        return authService.login(authRequest);
    }
}
