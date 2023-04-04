package com.matheusxreis.todo.services;

import com.matheusxreis.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository repo;
    @Autowired
    UserService(UserRepository repo){
       this.repo = repo;
    }
    public void login(){

    }

    public void register(String username, String password){

    }
}
