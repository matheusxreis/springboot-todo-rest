package com.matheusxreis.todo.models;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="task")
public class Task {

    public Task(String description, User owner){
        this.description = description;
        this.owner = owner;
    }
    public Task(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private Boolean done = false;
    @OneToOne()
    @JoinTable(name="users_tasks",
            inverseJoinColumns = @JoinColumn(
                    name="user_id",
                    referencedColumnName = "id"
            ),
            joinColumns = @JoinColumn(
                    name="task_id",
                    referencedColumnName = "id"
            )
        )
    private User owner;
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    @Nullable()
    private Timestamp doneAt;
    public long getId() {
        return id;
    }

    public Boolean getDone() {
        return done;
    }

    public String getDescription() {
        return description;
    }

    public User getOwner() {
        return owner;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(Timestamp doneAt) {
        this.doneAt = doneAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void mark(){
        this.done = !this.done;
    }

}
