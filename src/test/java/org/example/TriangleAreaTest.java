package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;


public class TriangleAreaTest {

    @Test
    @DisplayName("Площадь вычилить через основание и высоту")
    void testAreaByBaseAndHeight() {
        double result = TriangleArea.calculateSide(5, 4);
        assertEquals(10, result, 0.0001);
    }

    @ParameterizedTest
    @CsvSource({
            "5, 4, 10",
            "3, 6, 9",
            "2.5, 4, 5",
            "-10, 0.5, 2.5"  // тест должен упасть
    })
    void testAreaByBaseAndHeight(double a, double h, double expected) {
        double result = TriangleArea.calculateSide(a, h);
        assertEquals(expected, result, 0.0001);
    }
    @Test
    @DisplayName("Ошибка при отрицательном значкнии основания")
    void testNegativeBase() {
        assertThrows(IllegalArgumentException.class,
                () -> TriangleArea.calculateSide(-5, 4)
        );
    }
}

