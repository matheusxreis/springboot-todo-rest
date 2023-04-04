package com.matheusxreis.todo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
public class Repo {

    public TaskRepository task;
    @Autowired
    Repo(
        TaskRepository task
    ){
        this.task = task;
    }
}
