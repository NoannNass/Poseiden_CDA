package com.nnk.springboot.security;

import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil {

    private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // génération de clé automatique

    private static final long EXPIRATION_TIME = 86400000; // 24 heures en millisecondes

    //Génèrer un JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    //décoder et valider un JWT
    public String validateToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject(); // Retourne le nom d'utilisateur

    }

    //Vérifier la validité (expiration)
    public boolean isTokenExpired(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration().before(new Date());
    }
}
