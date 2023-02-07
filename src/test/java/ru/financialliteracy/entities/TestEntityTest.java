package ru.financialliteracy.entities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestEntityTest {
    ru.financialliteracy.entities.Test test =
            new ru.financialliteracy.entities.Test(
                    "А", "а", "Б", "б", "В");

    @Test
    void getAllAnswers() {
        assertEquals(test.getAllAnswers(), List.of("А", "а", "Б", "б", "В"));
    }

    @Test
    void countCorrectAnswers() {
        assertEquals(test.countCorrectAnswers(test.getAllAnswers(), List.of("а", "а", "а", "а", "а")), 2);
        assertEquals(test.countCorrectAnswers(test.getAllAnswers(), List.of("б", "б", "б", "б", "б")), 2);
        assertEquals(test.countCorrectAnswers(test.getAllAnswers(), List.of("В", "В", "В", "В", "В")), 1);
    }
}