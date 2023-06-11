package com.example.boot.utils;

public class DataValidation {
    //валідація даних
    public static boolean validateType(String type) {
        return type.equalsIgnoreCase("printer") || type.equalsIgnoreCase("writer");
    }
}
