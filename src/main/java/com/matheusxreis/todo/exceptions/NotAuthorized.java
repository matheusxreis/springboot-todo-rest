package com.matheusxreis.todo.exceptions;

public class NotAuthorized extends Exception {
    String message = "Not authorized";
    public NotAuthorized(){
        super();
    }
    public NotAuthorized(String message){
        super();
        this.message = String.format("%s because of %s", this.message, message);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
