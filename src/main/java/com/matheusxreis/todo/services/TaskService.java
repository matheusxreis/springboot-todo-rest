package com.matheusxreis.todo.services;


import com.matheusxreis.todo.exceptions.DataNotFound;
import com.matheusxreis.todo.models.Task;
import com.matheusxreis.todo.models.User;
import com.matheusxreis.todo.repositories.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public User getUserById(long userId) throws DataNotFound {
        Optional<User> user = this.repo.user.findById(userId);
        if(user.isPresent()){
            return user.get();
        }else {
            throw new DataNotFound("User");
        }
    }

    /// GET
    public List<Task> findAll(long userId) throws DataNotFound {

        return repo.task.findByOwner(getUserById(userId));

    }

    public Optional<Task> findById(long id, long userId) throws DataNotFound {
        return repo.task.findByIdAndOwner(id, getUserById(userId));
    }
    public List<Task> getAllDone(long userId) throws DataNotFound{
        return repo.task.findByDoneAndOwner(true, getUserById(userId));
    };
    public List<Task> getAllNotDone(long userId) throws DataNotFound {
        return repo.task.findByDoneAndOwner(false, getUserById(userId));
    }
    ////

    /// CREATE
    public Task save(String description, long userId) throws DataNotFound {
            Task task = new Task(description, getUserById(userId));
            return repo.task.save(task);

    }

    ///

    /// UPDATE
    public Task mark(long id, long userId) throws DataNotFound{
        Optional<Task> task = this.findById(id, userId);
        task.ifPresent(value -> {
            if(!value.getDone()){
                value.setDoneAt(new Timestamp(System.currentTimeMillis()));
            }
            value.mark();
            repo.task.save(value);
        });
        return task.get();
    }

    ///

    /// DELETE
    public void deleteAll(){
        repo.task.deleteAll();
    }
    public void delete(long id){
        repo.task.deleteById(id);
    }

    ///

}
