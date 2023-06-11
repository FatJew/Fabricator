package com.example.boot.model;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class Writer extends Goods {
    public Writer(long id, String name, String information, String year) {
        super(id, name, information, year);
    }

    @Override
    public String getInstance() {
        return Writer.class.getSimpleName().toLowerCase();
    }
}
