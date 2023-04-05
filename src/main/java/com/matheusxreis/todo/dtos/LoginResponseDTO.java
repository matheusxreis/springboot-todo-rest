package com.matheusxreis.todo.dtos;

import com.matheusxreis.todo.models.User;

public class LoginResponseDTO {
    public User user;
    public String token;
    public LoginResponseDTO(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
