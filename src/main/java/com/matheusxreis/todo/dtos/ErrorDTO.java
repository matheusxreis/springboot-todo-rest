package com.matheusxreis.todo.dtos;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorDTO {

    public ErrorDTO(
            Integer status,
            String error,
            String message,
            String path
    ){
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
    public Integer status;
    public String error;
    public String message;
    public String path;
    public LocalDateTime timestamp;

}
