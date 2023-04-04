package com.matheusxreis.todo.controllers;

import com.matheusxreis.todo.dtos.RegisterDTO;
import com.matheusxreis.todo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/u")
public class UserController {

    UserService service;
    @Autowired
    UserController(UserService service){
        this.service = service;
    }
    @PostMapping("/register")
    public void register(
            @RequestBody
            RegisterDTO registerDTO
    ){
        this.service.register(registerDTO.username, registerDTO.password);
    }

    @PostMapping("/login")
    public void login(){

    }
}
