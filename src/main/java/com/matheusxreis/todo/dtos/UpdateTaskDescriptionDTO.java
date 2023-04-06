package com.matheusxreis.todo.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UpdateTaskDescriptionDTO {
    @NotNull
    @NotEmpty
    public String description;
}
