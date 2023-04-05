package com.matheusxreis.todo.repositories;

import com.matheusxreis.todo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
