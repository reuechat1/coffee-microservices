package com.yan.authservice.services;

import com.yan.authservice.dtos.account.ClaimsRequest;
import com.yan.authservice.dtos.gateway.AuthRequest;
import com.yan.authservice.dtos.gateway.AuthResponse;
import com.yan.authservice.dtos.gateway.RefreshRequest;
import com.yan.authservice.exceptions.AppError;
import com.yan.authservice.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;
    private final JwtTokenUtil jwtTokenUtil;

    public ResponseEntity<?> login(AuthRequest authRequest) {
        try {
            ResponseEntity<?> claimsRequest = restTemplate.postForEntity("http://account-service/api/account/get_user_claims", authRequest, ClaimsRequest.class);
            Map<String, Object> dataFromClaimsRequest = new HashMap<>();
            dataFromClaimsRequest.put("role", claimsRequest.getBody());
            Claims claims = Jwts.claims(dataFromClaimsRequest);

            System.out.println(claimsRequest.getBody());
            String accessToken = jwtTokenUtil.generateAccessToken(authRequest.getUsername(), claims);

            AuthResponse authResponse = new AuthResponse(accessToken);
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_GATEWAY.value(), "Почта или пароль неверны"), HttpStatus.BAD_GATEWAY);
        }
    }
}
