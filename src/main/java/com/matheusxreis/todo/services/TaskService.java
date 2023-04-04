package com.matheusxreis.todo.services;


import com.matheusxreis.todo.dtos.SaveTaskDTO;
import com.matheusxreis.todo.models.Task;
import com.matheusxreis.todo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    TaskRepository repo;
    @Autowired
    TaskService(TaskRepository repo){
        this.repo = repo;
    }

    public Optional<Task> findById(long id) {
       return repo.findById(id);
    }
    public List<Task> findAll() {
        return repo.findAll();
    }

    public Task save(SaveTaskDTO dto) {
       Task task = new Task(dto.description, dto.owner);
       return repo.save(task);
    }

    public Task mark(long id){
        Optional<Task> task = this.findById(id);
        task.ifPresent(value -> {
            value.mark();
            repo.save(value);
        });
        return task.get();
    }

    public void deleteAll(){
        repo.deleteAll();
    }
    public void delete(long id){
        repo.deleteById(id);
    }
}
