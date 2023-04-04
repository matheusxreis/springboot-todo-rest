package com.matheusxreis.todo.models;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private Boolean done = false;
    private String owner;
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    private Timestamp doneAt;

}
