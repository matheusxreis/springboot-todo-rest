package com.matheusxreis.todo.exceptions;

public class UserAlreadyExist extends Exception{
    String message = "Username already exist.";
    public UserAlreadyExist(){
        super();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
