package com.matheusxreis.todo.repositories;

import com.matheusxreis.todo.models.Task;
import com.matheusxreis.todo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDone(boolean done);
    List<Task> findByOwner(User user);
    Optional<Task> findByIdAndOwner(long id, User user);
    List<Task> findByDoneAndOwner(boolean done, User user);
}
