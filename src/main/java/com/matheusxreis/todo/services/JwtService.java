package com.matheusxreis.todo.services;

import com.matheusxreis.todo.models.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService implements Serializable {

        @Value("${jwt.secret}")
        private String secret;

        public String generate(User user){
                Map<String, Object> claims = new HashMap<>();
                return create(claims, String.valueOf(user.getId()));
        }

        public DecodeReturnJwt decode(String token){

                if(token.contains("Bearer")){
                    token = token.replace("Bearer", "");
                }
                boolean expired;
                long id = 0;
                try {
                    id = getIdFromToken(token);
                    expired = isExpired(token);
                }catch(ExpiredJwtException exception){
                    System.out.println("cai na excep");
                    expired = true;
                }
            System.out.println("sai da mesma");

            return new DecodeReturnJwt(
                       id,
                       expired
               );
        }
        private String create(
                Map<String, Object> claim,
                String subject
        ){
        return Jwts.builder()
                .setClaims(claim)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(60).toInstant()))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }

        private long getIdFromToken(String token) throws ExpiredJwtException{
                String id = retrieveInfo(token).getSubject();
                return Long.parseLong(String.format("%s",id));
        };

        private boolean isExpired(String token) throws ExpiredJwtException{
               Date expiration = retrieveInfo(token).getExpiration();
               return expiration.before(new Date());
        }

        private io.jsonwebtoken.Claims retrieveInfo(String token) throws ExpiredJwtException {
                return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }


}
