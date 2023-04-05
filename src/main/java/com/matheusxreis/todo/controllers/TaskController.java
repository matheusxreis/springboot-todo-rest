package com.matheusxreis.todo.controllers;


import com.matheusxreis.todo.dtos.SaveTaskDTO;
import com.matheusxreis.todo.models.Task;
import com.matheusxreis.todo.repositories.TaskRepository;
import com.matheusxreis.todo.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/tasks")
public class TaskController {

    TaskService service;

    @Autowired
    TaskController(TaskService service){
        this.service = service;
    }


    @GetMapping()
    public List<Task> listAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Task>> getTask(@PathVariable(value="id") long id){
        Optional<Task> task = service.findById(id);
       if(task.isPresent()){
           return ResponseEntity.ok(task);
       }else {
           return ResponseEntity.badRequest().build();
       }
    }

    @GetMapping("/done")
    public ResponseEntity<List<Task>> getAllDone() {
        return ResponseEntity.ok(service.getAllDone());
    }
    @GetMapping("/not/done")
    public ResponseEntity<List<Task>> getAllNotDone() {
        return ResponseEntity.ok(service.getAllNotDone());
    }

    @PostMapping()
    public ResponseEntity saveTask(
            @Valid
            @RequestBody
            SaveTaskDTO data
            ) throws Exception {


        service.save(data);
        URI uri = URI.create("/task");
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping("{id}")
    public ResponseEntity mark(
            @PathVariable(value="id") long id
    ){
       Task task = service.mark(id);
        if(task != null){
            return ResponseEntity.ok(task);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping()
    public void removeAll(){
        service.deleteAll();

    }

    @DeleteMapping("{id}")
    public void removeById(
            @PathVariable(value="id") long id
    ){
        service.delete(id);
    }

}
