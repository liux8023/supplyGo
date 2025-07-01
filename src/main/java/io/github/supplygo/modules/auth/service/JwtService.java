package io.github.supplygo.modules.auth.service;

import io.github.supplygo.modules.system.entity.SysUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private final SecretKey secretKey;
    private final long expirationHours;

    public JwtService(@Value("${spring.security.oauth2.resourceserver.jwt.secret-key}") String secretKeyString,
                      @Value("${jwt.expiration-in-hours}") long expirationHours) {
        this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes(StandardCharsets.UTF_8));
        this.expirationHours = expirationHours;
    }


    public String generateToken(SysUser user) {
        Map<String, Object> claims = new HashMap<>();
        // 您可以在这里添加自定义的声明
        claims.put("userId", user.getId());
        claims.put("username", user.getName());

        return Jwts.builder()
                .claims(claims)
                .subject(user.getAccount())
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(expirationHours, ChronoUnit.HOURS)))
                .signWith(secretKey)
                .compact();
    }
} 