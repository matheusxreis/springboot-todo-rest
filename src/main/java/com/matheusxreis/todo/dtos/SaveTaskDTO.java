package com.matheusxreis.todo.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SaveTaskDTO {
    @NotNull
    @NotEmpty
    public String description;
    public String owner;
}
