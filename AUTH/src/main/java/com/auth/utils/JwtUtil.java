package com.auth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;


@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(String email){
        Map<String , Object> claims = new HashMap<>();
        claims.put("emails" ,email);
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 *10))
                .claims(claims)
                .signWith(key())
                .compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    private Claims getClaims(String token){
        return Jwts.parser().verifyWith((SecretKey) key())
                .build().parseSignedClaims(token).getPayload();
    }

    public String getEmailFromToken(String token){
        return getClaims(token).get("emails", String.class);
    }

    public Date extractExpiration(String token){
        return getClaims(token).getExpiration();
    }
}
