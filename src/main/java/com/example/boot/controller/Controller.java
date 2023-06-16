package com.example.boot.controller;

import com.example.boot.mapper.RequestToEntityMapper;
import com.example.boot.model.Request;
import com.example.boot.service.Service;
import com.example.boot.utils.DataValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/printers")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Controller {
    private final Service service;
    private final RequestToEntityMapper requestToEntityMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Request request, @RequestParam String type) {
        if (DataValidation.validateType(type)) {
            return new ResponseEntity<>(service.create(requestToEntityMapper.getEntityFromRequest(request, type), type), HttpStatus.CREATED);
        }
        throw new RuntimeException("Bad request");
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam String type) {
        if (DataValidation.validateType(type)) {
            return ResponseEntity.ok(service.getAllPrinters(type));
        }
        throw new RuntimeException("Bad request");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id, @RequestParam String type) {
        if (DataValidation.validateType(type)) {
            return ResponseEntity.ok(service.getPrinterById(id, type));
        }
        throw new RuntimeException("Bad request");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestParam String type, @RequestBody Request request) {
        if (DataValidation.validateType(type)) {
            return ResponseEntity.ok(service.updatePrinter(id, requestToEntityMapper.getEntityFromRequest(request, type), type));
        }
        throw new RuntimeException("Bad request");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id, @RequestParam String type) {
        if (DataValidation.validateType(type)) {
            service.delete(id, type);
            return ResponseEntity.ok("deleted");
        }
        throw new RuntimeException("Bad request");
    }


    @GetMapping("equipment")
    public ResponseEntity<?> getEquipment() throws IOException {
        return ResponseEntity.ok(service.getEquipment());
    }
}
