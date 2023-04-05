package com.matheusxreis.todo.exceptions;

public class DataNotFound extends Exception {
    String message = "";
    public DataNotFound(String x){
        super();
        this.message = String.format("%s was not found", x);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
