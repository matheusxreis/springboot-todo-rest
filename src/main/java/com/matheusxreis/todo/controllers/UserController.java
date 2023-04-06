package com.matheusxreis.todo.controllers;

import com.matheusxreis.todo.dtos.DeleteProfileDTO;
import com.matheusxreis.todo.dtos.LoginDTO;
import com.matheusxreis.todo.dtos.LoginResponseDTO;
import com.matheusxreis.todo.dtos.RegisterDTO;
import com.matheusxreis.todo.exceptions.AuthenticationInvalid;
import com.matheusxreis.todo.exceptions.DataNotFound;
import com.matheusxreis.todo.exceptions.NotAuthorized;
import com.matheusxreis.todo.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/u")
public class UserController {

    UserService service;
    @Autowired
    UserController(UserService service){
        this.service = service;
    }

    private long getUserIdFromReq(HttpServletRequest request
    ){
        return Long.parseLong(String.valueOf(request.getAttribute("userId")));
    }
    @PostMapping("/register")
    public void register(
            @RequestBody
            RegisterDTO registerDTO
    ){
        this.service.register(registerDTO.username, registerDTO.password);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody
            LoginDTO loginDTO
    ) throws AuthenticationInvalid {

        LoginResponseDTO response = this.service.login(loginDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/remove/account")
    public void removeAccount(@RequestBody DeleteProfileDTO dto,
                              HttpServletRequest request) throws NotAuthorized, DataNotFound {
            this.service.remove(dto, getUserIdFromReq(request));
    }
}
