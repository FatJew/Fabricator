package com.example.boot.repository;

import com.example.boot.model.Equipment;
import com.example.boot.model.Goods;
import com.example.boot.model.Printer;
import com.example.boot.model.Writer;
import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@org.springframework.stereotype.Repository
public class Repository {

    private final String[] tableNames = {"id", "name", "information", "year"};

    private final Map<String, Map<Long, Goods>> repo = new HashMap<>();

    private void preEditAction(String type) {
        try {
            CSVWriter csvWriter = new CSVWriter(
                    new FileWriter(
                            type + "-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()) + ".csv"));
            csvWriter.writeNext(tableNames);
            repo.get(type).forEach((key, value) -> csvWriter.writeNext(new String[]{
                    String.valueOf(value.getId()), value.getName(), value.getInformation(), value.getYear()}));

            csvWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //тип класу printer або writer
    private String getClassType(String type) {
        if (type.equalsIgnoreCase("printer")) {
            return Printer.class.getSimpleName().toLowerCase();
        }
        return Writer.class.getSimpleName().toLowerCase();
    }

    public Goods create(Goods goods, String type) {
        String classType = getClassType(type);
        goods.setId(repo.getOrDefault(classType, new HashMap<>()).size() + 1);

        //апдейтимо мапу
        Map<Long, Goods> longGoodsMap = repo.getOrDefault(classType, new HashMap<>());
        longGoodsMap.put(goods.getId(), goods);
        repo.put(classType, longGoodsMap);

        //апдейтимо csv файл
        preEditAction(classType);
        return goods;
    }

    public List<Goods> getAllGoods(String type) {
        if (repo.isEmpty()) {
            return List.of();
        }
        return repo.get(type).values().stream().toList();
    }

    public Optional<Goods> getPrinterById(long id, String type) {
        if (repo.containsKey(getClassType(type))) {
            return Optional.ofNullable(repo.get(type).get(id));
        }
        return Optional.empty();
    }

    public Optional<Goods> updatePrinter(long id, Goods goods, String type) {
        String classType = getClassType(type);
        goods.setId(id);
        if (existById(id, classType)) {
            //апдейтимо мапу
            Map<Long, Goods> longGoodsMap = repo.get(classType);
            longGoodsMap.replace(id, goods);
            repo.put(classType, longGoodsMap);

            preEditAction(classType);
            return Optional.of(goods);
        }
        return Optional.empty();
    }

    //перевірка чи існує елемент
    private boolean existById(long id, String type) {
        return repo.get(type).get(id) != null;
    }

    public void delete(long id, String type) {
        String classType = getClassType(type);
        //апдейтимо мапу
        Map<Long, Goods> longGoodsMap = repo.get(classType);
        longGoodsMap.remove(id);
        repo.put(classType, longGoodsMap);
        preEditAction(type);
    }

    //метод для заповнення мапи елементами при запуску програми
    public void putReadValues(Map<String, Map<Long, Goods>> goodsValues) {
        repo.putAll(goodsValues);
    }


    //2 частина завдання. отримання обладнання
    public List<Equipment> getEquipment() throws IOException {
        List<Equipment> equipmentList = new ArrayList<>();

        FileReader fileReader = new FileReader("equipment.csv");
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

        for (CSVRecord record : csvParser) {
            long id = Long.parseLong(record.get("id"));
            String name = record.get("name");
            int count = Integer.parseInt(record.get("count"));

            Equipment equipment = new Equipment(id, name, count);
            equipmentList.add(equipment);
        }
        return equipmentList;
    }
}
