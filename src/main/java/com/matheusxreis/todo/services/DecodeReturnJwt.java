package com.matheusxreis.todo.services;

public class DecodeReturnJwt {
    public long userId;
    public boolean isExpired;

    public DecodeReturnJwt(
            long userId,
            boolean isExpired
    ) {
        this.userId = userId;
        this.isExpired = isExpired;
    }
}
