package com.matheusxreis.todo.models;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="task")
public class Task {

    public Task(String description, String owner){
        this.description = description;
        this.owner = owner;
    }
    public Task(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private Boolean done = false;
    private String owner;
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    @Nullable()
    private Timestamp doneAt;

    public void mark(){
        this.done = !this.done;
    }
}
