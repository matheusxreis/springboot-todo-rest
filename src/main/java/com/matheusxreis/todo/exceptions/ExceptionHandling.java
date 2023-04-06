package com.matheusxreis.todo.exceptions;

import com.matheusxreis.todo.dtos.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.xml.crypto.Data;
import java.util.NoSuchElementException;

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
                exception.toString(),
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

    @ExceptionHandler(value={AuthenticationInvalid.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleAuthenticationInvalid(
            AuthenticationInvalid exception,
            HttpServletRequest req
    ){
        return new ErrorDTO(
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.name(),
                exception.getMessage(),
                req.getServletPath()
                );
    }

    @ExceptionHandler(value={NoSuchElementException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleNoSuchElement(
            NoSuchElementException exception,
            HttpServletRequest req
    ){
        return new ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                "There is no content for you",
                req.getServletPath());
    }

    @ExceptionHandler(value={NotAuthorized.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO hanldeNotAuthorized(
            NotAuthorized exception,
            HttpServletRequest req
    ) {
        return new ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                exception.message,
                req.getServletPath()
        );
    }
}
