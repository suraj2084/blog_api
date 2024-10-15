package com.suraj.blog_api.surajblogapi.Services;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

import javax.crypto.*;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.security.NoSuchAlgorithmException;

@Service
public class JWTServices {

    private SecretKey sk;

    public JWTServices() {

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            sk = keyGenerator.generateKey();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1))
                .and()
                .signWith(sk)
                .compact();
    }

    // private SecretKey getKey() {
    // byte[] byteKey = Decoders.BASE64.decode(sk);
    // return Keys.hmacShaKeyFor(byteKey);
    // // return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // }

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(sk)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extraactExpiration(token).before(new Date());
    }

    private Date extraactExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

}
