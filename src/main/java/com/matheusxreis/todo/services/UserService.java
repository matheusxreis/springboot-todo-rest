package com.matheusxreis.todo.services;

import com.matheusxreis.todo.models.User;
import com.matheusxreis.todo.repositories.Repo;
import com.matheusxreis.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.net.PasswordAuthentication;

@Service
public class UserService {

    Repo repo;
    PasswordEncoder encoder;
    @Autowired
    UserService(Repo repo, PasswordEncoder encoder){
       this.repo = repo;
       this.encoder = encoder;
    }
    public void login(){

    }

    public void register(String username, String password){

        User user = new User(username,encoder.encode(password));
        this.repo.user.save(user);
    }

    public void remove(long id){
        this.repo.user.deleteById(id);
    }
}
