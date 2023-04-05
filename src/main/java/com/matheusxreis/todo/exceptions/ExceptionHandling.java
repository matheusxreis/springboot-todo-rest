package com.matheusxreis.todo.exceptions;

import com.matheusxreis.todo.dtos.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.xml.crypto.Data;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(value= {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleServerError(
            Exception exception,
            HttpServletRequest req
    ){
       return new ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                exception.getMessage(),
                req.getServletPath()
        );
    }

    @ExceptionHandler(value={DataNotFound.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleDataNotFound(
            DataNotFound exception,
            HttpServletRequest req
    ){
        return new ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                exception.getMessage(),
                req.getServletPath()
        );
    }
}
