package com.matheusxreis.todo.services;


import com.matheusxreis.todo.dtos.SaveTaskDTO;
import com.matheusxreis.todo.exceptions.DataNotFound;
import com.matheusxreis.todo.models.Task;
import com.matheusxreis.todo.models.User;
import com.matheusxreis.todo.repositories.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
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

    public Task save(SaveTaskDTO dto) throws DataNotFound {
        User user = repo.user.findByUsername(dto.owner);
        if(user!=null) {
            Task task = new Task(dto.description, user);
            return repo.task.save(task);
        }else {
            throw new DataNotFound("User");
        }
    }

    public Task mark(long id){
        Optional<Task> task = this.findById(id);
        task.ifPresent(value -> {
            if(!value.getDone()){
                value.setDoneAt(new Timestamp(System.currentTimeMillis()));
            }
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
