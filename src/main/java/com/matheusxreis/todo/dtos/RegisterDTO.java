package com.matheusxreis.todo.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RegisterDTO {
    @NotNull
    @NotEmpty
    public String username;
    @NotNull
    @NotEmpty
    public String password;
}
