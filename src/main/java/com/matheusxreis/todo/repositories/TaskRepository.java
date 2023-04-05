package com.matheusxreis.todo.repositories;

import com.matheusxreis.todo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDone(boolean done);
}
