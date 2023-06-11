package com.example.boot.mapper;

import com.example.boot.model.Goods;
import com.example.boot.model.Printer;
import com.example.boot.model.Request;
import com.example.boot.model.Writer;
import org.springframework.stereotype.Component;

@Component
public class RequestToEntityMapper {
    //перетворення реквесту моделі у printer або writer
    public Goods getEntityFromRequest(Request requestBody, String type) {
        if (type.equalsIgnoreCase("printer")) {
            return new Printer(0, requestBody.getName(), requestBody.getInformation(), requestBody.getYear());
        }
        return new Writer(0, requestBody.getName(), requestBody.getInformation(), requestBody.getYear());
    }
}
