package com.example.boot.model;

import lombok.Data;

@Data
public class Equipment {
    private long id;
    private String name;
    private int count;

    public Equipment(long id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }
}
