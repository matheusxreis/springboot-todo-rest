package com.matheusxreis.todo.models;

import jakarta.persistence.*;

@Entity
@Table(name="requests")
public class Request {

    public Request(
            User inviteBy,
            User toWho
    ){
        this.inviteBy = inviteBy;
        this.toWho = toWho;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @OneToOne()
    User inviteBy;
    @OneToOne()
    User toWho;
    Integer status = 0;
}
