package com.matheusxreis.todo.services;

import com.matheusxreis.todo.dtos.LoginDTO;
import com.matheusxreis.todo.dtos.LoginResponseDTO;
import com.matheusxreis.todo.exceptions.AuthenticationInvalid;
import com.matheusxreis.todo.exceptions.DataNotFound;
import com.matheusxreis.todo.models.User;
import com.matheusxreis.todo.repositories.Repo;
import com.matheusxreis.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
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
    public LoginResponseDTO login(LoginDTO dto) throws AuthenticationInvalid {
       User user = this.repo.user.findByUsername(dto.username);

       if(user == null) {
           throw new AuthenticationInvalid();
       }
        String encryptPassword = user.revealPassword();

        boolean isPasswordRight = encoder.matches(dto.password, encryptPassword);

       if(!isPasswordRight){
           throw new AuthenticationInvalid();
       }

       return new LoginResponseDTO(
               user,
               ""
       );

    }

    public void register(String username, String password){

        User user = new User(username,encoder.encode(password));
        this.repo.user.save(user);
    }

    public void remove(long id){
        this.repo.user.deleteById(id);
    }
}
