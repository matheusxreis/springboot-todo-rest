package com.matheusxreis.todo.exceptions;

public class AuthenticationInvalid extends Exception {
    String message = "Username or password invalid.";
    public AuthenticationInvalid(){
        super();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
