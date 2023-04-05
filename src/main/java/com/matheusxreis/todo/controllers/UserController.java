package com.matheusxreis.todo.controllers;

import com.matheusxreis.todo.dtos.RegisterDTO;
import com.matheusxreis.todo.services.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/remove/{id}")
    public void removeAccount(@PathVariable("id") long id) {
            this.service.remove(id);
    }
}
