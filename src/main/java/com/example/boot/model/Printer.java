package com.example.boot.model;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class Printer extends Goods {
    public Printer(long id, String name, String information, String year) {
        super(id, name, information, year);
    }

    @Override
    public String getInstance() {
        return Printer.class.getSimpleName().toLowerCase();
    }
}
