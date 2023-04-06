package com.matheusxreis.todo.controllers;


import com.matheusxreis.todo.dtos.SaveTaskDTO;
import com.matheusxreis.todo.exceptions.DataNotFound;
import com.matheusxreis.todo.models.Task;
import com.matheusxreis.todo.services.TaskService;
import jakarta.servlet.http.HttpServletRequest;
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

    private long getUserIdFromReq(HttpServletRequest request
    ){
        return Long.parseLong(String.valueOf(request.getAttribute("userId")));
    }

    // GET ROUTES =================
    @GetMapping()
    public List<Task> listAll(
            HttpServletRequest request
    ) throws DataNotFound {
        return service.findAll(getUserIdFromReq(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> getTask(@PathVariable(value="id") long id,
                                                  HttpServletRequest request) throws DataNotFound{
        Optional<Task> task = service.findById(id, getUserIdFromReq(request));
       if(task!=null){
           return ResponseEntity.ok(task.get());
       }else {
           return ResponseEntity.badRequest().build();
       }
    }

    @GetMapping("/done")
    public ResponseEntity<List<Task>> getAllDone(
            HttpServletRequest request
    ) throws DataNotFound {
        return ResponseEntity.ok(service.getAllDone(getUserIdFromReq(request)));
    }
    @GetMapping("/not/done")
    public ResponseEntity<List<Task>> getAllNotDone(
            HttpServletRequest request
    ) throws DataNotFound {
        return ResponseEntity.ok(service.getAllNotDone(getUserIdFromReq(request)));
    }

    /// ===============

    // POST ===========
    @PostMapping()
    public ResponseEntity saveTask(
            @Valid
            @RequestBody
            SaveTaskDTO data,
            HttpServletRequest request
            ) throws DataNotFound {


        service.save(data.description, getUserIdFromReq(request));
        URI uri = URI.create("/task");
        return ResponseEntity.created(uri).build();
    }

    // ==========

    // UPDATE ===============
    @PatchMapping("{id}")
    public ResponseEntity mark(
            @PathVariable(value="id") long id,
            HttpServletRequest request
    ) throws DataNotFound{
       Task task = service.mark(id, getUserIdFromReq(request));
        if(task != null){
            return ResponseEntity.ok(task);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    // ==========

    // DELETING
    @DeleteMapping()
    public void removeAll(
            HttpServletRequest request
    ) throws DataNotFound {
        service.deleteAll(getUserIdFromReq(request));

    }

    @DeleteMapping("{id}")
    public void removeById(
            @PathVariable(value="id") long id,
            HttpServletRequest request
    ) throws DataNotFound {
        service.delete(id, getUserIdFromReq(request));
    }
    ////

}
