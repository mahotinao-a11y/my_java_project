package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @ParameterizedTest
    @DisplayName("Сложение двух чисел")
    @CsvSource({
            "2, 3, 5",
            "-1, 1, 0",
            "0, 0, 0",
            "100, 200, 300",
            "-5, -3, -8"
    })
    void add(int a, int b, int expected) {
        assertEquals(expected, Calculator.summ(a, b));
    }

    @ParameterizedTest
    @DisplayName("Вычитание двух чисел")
    @CsvSource({
            "10, 3, 7",
            "5, 10, -5",
            "0, 5, -5",
            "-10, -3, -7",
            "100, 50, 50"
    })
    void subtract(int a, int b, int expected) {
        assertEquals(expected, Calculator.subtract(a, b));
    }

    @ParameterizedTest
    @DisplayName("Деление двух чисел")
    @CsvSource({
            "10, 2, 5.0",
            "7, 2, 3.5",
            "-10, 2, -5.0",
            "10, -2, -5.0",
            "0, 5, 0.0"
    })
    void divideValid(int a, int b, double expected) {
        assertEquals(expected, Calculator.divide(a, b));
    }

    @Test
    @DisplayName("Деление на ноль выбрасывает исключение")
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class,
                () -> Calculator.divide(10, 0), "Деление на ноль должно выбрасывать IllegalArgumentException"
        );
    }
}
