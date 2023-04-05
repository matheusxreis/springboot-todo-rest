package com.matheusxreis.todo.services;


import com.matheusxreis.todo.dtos.SaveTaskDTO;
import com.matheusxreis.todo.models.Task;
import com.matheusxreis.todo.repositories.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    Repo repo;
    @Autowired
    TaskService(Repo repo){
        this.repo = repo;
    }

    public Optional<Task> findById(long id) {
       return repo.task.findById(id);
    }
    public List<Task> findAll() {
        return repo.task.findAll();
    }

    public Task save(SaveTaskDTO dto) {
       Task task = new Task(dto.description, dto.owner);
       return repo.task.save(task);
    }

    public Task mark(long id){
        Optional<Task> task = this.findById(id);
        task.ifPresent(value -> {
            value.mark();
            repo.task.save(value);
        });
        return task.get();
    }

    public void deleteAll(){
        repo.task.deleteAll();
    }
    public void delete(long id){
        repo.task.deleteById(id);
    }

    public List<Task> getAllDone(){
        return repo.task.findByDone(true);
    };
    public List<Task> getAllNotDone(){
        return repo.task.findByDone(false);
    }
}
