package com.example.boot;

import com.example.boot.model.Goods;
import com.example.boot.model.Printer;
import com.example.boot.model.Writer;
import com.example.boot.repository.Repository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @Bean
    //метод для заповнення мапи елементами при запуску програми
    public CommandLineRunner CommandLineRunnerBean(Repository repository) {
        return (args) -> {
            Map<String, Map<Long, Goods>> goodsValues = new HashMap<>();
            List<String> fileNames = List.of("printer-2023-06-09.csv", "writer-2023-06-09.csv");
            for (String fileName : fileNames) {
                String key = extractKeyFromFileName(fileName);
                try (FileReader fileReader = new FileReader(fileName);
                     CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
                    for (CSVRecord record : csvParser) {
                        long id = Long.parseLong(record.get("id"));
                        String name = record.get("name");
                        String information = record.get("information");
                        String year = record.get("year");
                        Goods goods = createGoods(key, id, name, information, year);
                        goodsValues.putIfAbsent(key, new HashMap<>());
                        goodsValues.get(key).put(id, goods);
                    }
                }
            }
            repository.putReadValues(goodsValues);
        };
    }

    //отримання типу класу з csv файлу
    private String extractKeyFromFileName(String fileName) {
        String key = fileName.toLowerCase();
        if (key.contains("printer")) {
            return "printer";
        }
        return "writer";
    }

    private Goods createGoods(String key, long id, String name, String information, String year) {
        if ("printer".equals(key)) {
            return new Printer(id, name, information, year);
        }
        return new Writer(id, name, information, year);
    }
}
