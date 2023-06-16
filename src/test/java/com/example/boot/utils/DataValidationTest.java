package com.example.boot.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataValidationTest {

    @Test
    void validateType() {
        assertTrue(DataValidation.validateType("printer"));
        assertTrue(DataValidation.validateType("writer"));
        assertFalse(DataValidation.validateType("another"));
    }
}