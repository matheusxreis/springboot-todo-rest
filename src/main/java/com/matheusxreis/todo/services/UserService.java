package com.matheusxreis.todo.services;

import com.matheusxreis.todo.dtos.DeleteProfileDTO;
import com.matheusxreis.todo.dtos.LoginDTO;
import com.matheusxreis.todo.dtos.LoginResponseDTO;
import com.matheusxreis.todo.exceptions.AuthenticationInvalid;
import com.matheusxreis.todo.exceptions.DataNotFound;
import com.matheusxreis.todo.exceptions.NotAuthorized;
import com.matheusxreis.todo.models.User;
import com.matheusxreis.todo.repositories.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {

    Repo repo;
    PasswordEncoder encoder;
    JwtService jwtService;
    @Autowired
    UserService(Repo repo, PasswordEncoder encoder, JwtService jwtService){
       this.repo = repo;
       this.encoder = encoder;
       this.jwtService = jwtService;
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
           jwtService.generate(user)
       );

    }

    public void register(String username, String password){

        User user = new User(username,encoder.encode(password));
        this.repo.user.save(user);

    }

    public void remove(DeleteProfileDTO dto, long userId) throws NotAuthorized, DataNotFound {

        if(!dto.password.equals(dto.confirmationPassword)){
            throw new NotAuthorized("password and confirmation are not equal");
        }

        Optional<User> user = this.repo.user.findById(userId);
        AtomicInteger notAuthorized = new AtomicInteger(0);
        user.ifPresentOrElse(u -> {
            boolean isPasswordRight = encoder.matches(dto.password, u.revealPassword());

            if(isPasswordRight){
                this.repo.user.deleteById(userId);
            }else {
                notAuthorized.set(1);
            }
        },
        ()->{
            notAuthorized.set(2);
        });

        switch (notAuthorized.get()){
            case 1: {
                throw new NotAuthorized("password is not valid.");
            }
            case 2: {
                throw new DataNotFound("User");
            }
        }


    }
}
