package com.example.boot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

//загальних клас
@Data
public abstract class Goods {
    private long id;
    private String name;
    private String information;
    private String year;

    public Goods() {

    }

    public Goods(long id, String name, String information, String year) {
        this.id = id;
        this.name = name;
        this.information = information;
        this.year = year;
    }

    @JsonIgnore
    public abstract String getInstance();
}

