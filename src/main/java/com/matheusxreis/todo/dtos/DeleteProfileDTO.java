package com.matheusxreis.todo.dtos;

public class DeleteProfileDTO {
    public String password;
    public String confirmationPassword;

    public DeleteProfileDTO(
            String password,
            String confirmationPassword
    ){
        this.password = password;
        this.confirmationPassword = confirmationPassword;
    }
}
