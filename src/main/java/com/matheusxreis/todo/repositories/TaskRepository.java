package com.matheusxreis.todo.repositories;

import com.matheusxreis.todo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {}
