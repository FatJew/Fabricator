package com.example.boot.service;

import com.example.boot.model.Equipment;
import com.example.boot.model.Goods;
import com.example.boot.repository.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public Goods create(Goods goods, String type) {
        return repository.create(goods, type);
    }

    public List<Goods> getAllPrinters(String type) {
        return repository.getAllGoods(type);
    }

    public List<Equipment> getEquipment() throws IOException {
        return repository.getEquipment();
    }

    public Optional<Goods> getPrinterById(long id, String type) {
        return repository.getPrinterById(id, type);
    }

    public Optional<Goods> updatePrinter(long id, Goods goods, String type) {
        return repository.updatePrinter(id, goods, type);
    }

    public void delete(long id, String type) {
        repository.delete(id, type);
    }
}
