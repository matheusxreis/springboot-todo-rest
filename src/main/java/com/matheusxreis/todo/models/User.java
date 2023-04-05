package com.matheusxreis.todo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Collection;

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
    public User(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    private Timestamp registeredAt = new Timestamp(System.currentTimeMillis());

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name="users_tasks",
            joinColumns = @JoinColumn(
                    name="user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name="task_id",
                    referencedColumnName = "id"
            )
    )
    private Collection<Task> tasks;

    public long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public String revealPassword() {
        return password;
    }
}
