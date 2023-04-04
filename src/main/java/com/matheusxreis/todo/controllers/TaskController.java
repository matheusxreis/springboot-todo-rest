package com.matheusxreis.todo.controllers;


import com.matheusxreis.todo.models.Task;
import com.matheusxreis.todo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/tasks")
public class TaskController {

    @Autowired
    TaskRepository repo;

    @GetMapping()
    public List<Task> listAll(){
        return repo.findAll();
    }


}
