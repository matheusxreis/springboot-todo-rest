package com.matheusxreis.todo.controllers;


import com.matheusxreis.todo.dtos.SaveTaskDTO;
import com.matheusxreis.todo.models.Task;
import com.matheusxreis.todo.repositories.TaskRepository;
import org.hibernate.mapping.Any;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(value="/tasks")
public class TaskController {

    @Autowired
    TaskRepository repo;

    @GetMapping()
    public List<Task> listAll(){
        return repo.findAll();
    }

    @GetMapping("{id}")
    public Optional<Task> getTask(@PathVariable(value="id") long id){
        return repo.findById(id);
    }

    @PostMapping()
    public void saveTask(
            @RequestBody
            SaveTaskDTO data
            ){

        Task task = new Task(
                data.description,
                data.owner
        );
        repo.save(task);
    }

    @PatchMapping("{id}")
    public void mark(
            @PathVariable(value="id") long id
    ){
       Optional<Task> task = repo.findById(id);
       task.ifPresent(value -> {
           value.mark();
           repo.save(value);
       });
    }


}
