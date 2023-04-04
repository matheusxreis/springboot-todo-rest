package com.matheusxreis.todo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Entity
@Table(name="users")
public class User {

    public User(
            String username,
            String password
    ){
        this.username = username;
        this.password = password;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    private Timestamp registeredAt = new Timestamp(System.currentTimeMillis());

    public long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

}
