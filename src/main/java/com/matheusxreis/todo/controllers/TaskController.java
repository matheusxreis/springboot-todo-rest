package com.matheusxreis.todo.controllers;


import com.matheusxreis.todo.dtos.SaveTaskDTO;
import com.matheusxreis.todo.models.Task;
import com.matheusxreis.todo.repositories.TaskRepository;
import org.hibernate.mapping.Any;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<Optional<Task>> getTask(@PathVariable(value="id") long id){
        Optional<Task> task = repo.findById(id);
       if(task.isPresent()){
           return ResponseEntity.ok(task);
       }else {
           return ResponseEntity.badRequest().build();
       }
    }

    @PostMapping()
    public ResponseEntity saveTask(
            @RequestBody
            SaveTaskDTO data
            ){

        Task task = new Task(
                data.description,
                data.owner
        );
        repo.save(task);
        URI uri = URI.create("/task");
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping("{id}")
    public ResponseEntity mark(
            @PathVariable(value="id") long id
    ){
       Optional<Task> task = repo.findById(id);
       task.ifPresent(value -> {
           value.mark();
           repo.save(value);
       });

        if(task.isPresent()){
            return ResponseEntity.ok(task);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping()
    public void removeAll(){
        repo.deleteAll();

    }

    @DeleteMapping("{id}")
    public void removeById(
            @PathVariable(value="id") long id
    ){
        repo.deleteById(id);
    }

}
