package com.matheusxreis.todo.services;

import com.matheusxreis.todo.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService implements Serializable {

        @Value("${jwt.secret}")
        private String secret;

        public String generate(User user){
                Map<String, Object> claims = new HashMap<>();
                return create(claims, user.getUsername());
        }
        private String create(
                Map<String, Object> claim,
                String subject
        ){
        return Jwts.builder()
                .setClaims(claim)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }
}
