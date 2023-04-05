package com.matheusxreis.todo.filters;

import com.matheusxreis.todo.services.DecodeReturnJwt;
import com.matheusxreis.todo.services.JwtService;
import com.matheusxreis.todo.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    JwtService jwtService;
    public JwtRequestFilter(JwtService jwtService){
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = req.getHeader("Authorization");
        String path = req.getRequestURI();

        if(path.equals("/u/login") || path.equals("/u/register")){
            filterChain.doFilter(req, response);
            return;
        }
        if(authorization==null || !authorization.startsWith("Bearer")){
           response.sendError(HttpStatus.UNAUTHORIZED.value(),
                   "User not authorized to access this route.");
        }

        DecodeReturnJwt value =this.jwtService.decode(authorization);
        if(value.isExpired){
            response.sendError(HttpStatus.UNAUTHORIZED.value(),
                    "User not authorized to access this route.");
        }
        req.setAttribute("userId", value.userId);
       filterChain.doFilter(req, response);
    }


}
