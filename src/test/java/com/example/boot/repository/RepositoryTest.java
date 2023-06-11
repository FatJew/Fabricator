package com.example.boot.repository;

import com.example.boot.model.Printer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class RepositoryTest {
    @InjectMocks
    private Repository repository;

    @Test
    void create() {
        Printer printer = new Printer(0, "name", "inf", "year");
        assertNotNull(repository.create(printer, "printer"));
    }

    @Test
    void getAllGoods() {
        assertNotNull(repository.getAllGoods("printer"));
    }

    @Test
    void getPrinterById() {
        assertNotNull(repository.getPrinterById(1, "printer"));
    }

    @Test
    void updatePrinter() {
        Printer printer = new Printer(1, "name", "inf", "year");
        repository.create(printer, "printer");
        assertNotNull(repository.updatePrinter(1, printer, "printer"));
    }

    @Test
    void getEquipment() throws IOException {
        assertNotNull(repository.getEquipment());
    }
}