package com.yan.authservice.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil {

    @Value("${jwt.secret.access}")
    private String accessSecret;

    public String generateAccessToken(String email, Claims claims){
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(15).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);

        claims.put("exp", accessExpiration);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(accessExpiration)
                .signWith(SignatureAlgorithm.HS256, accessSecret)
                .setClaims(claims)
                .compact();
    }
}
