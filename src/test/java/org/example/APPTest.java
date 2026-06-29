package org.example;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class APPTest {// класс с тестами

    @Test
    @DisplayName("a больше b")
    void compareFirstGreater() {
        int a = 5; // подготовка данных
        int b = 3;
        String expected = "5 больше 3";
        String actual = NumberComparator.compare(a, b);// дейсвие, сравнение чисел
        assertEquals(expected, actual);// проверка ожидаемого результатс актуальным
    }

    @Test
    @DisplayName("a меньше b")
    void compareFirstLess() {
        assertEquals("-5 меньше 0", NumberComparator.compare(-5, 0));
        //запись короче
    }

    @ParameterizedTest
    @CsvSource({
            "5,5,0",
            "10,10,0",
            "-4,-4,0"
    })
    @DisplayName("a=b")
    void compareEqual(int a,int b,int expected) {
        int actual = NumberComparator.compareToInt(a,b);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Возвращает 1, когда a > b")
    void compareToIntFirstGreater() {
        assertEquals(1, NumberComparator.compareToInt(5, 3));
        assertTrue(NumberComparator.compareToInt(10, 0) > 0);
        /* вариант с полным кодом
        void compareToIntFirstGreater() {
        int a = 5; подготовка
        int b = 3;
        int expected = 1;
        int actual = NumberComparator.compareToInt(a, b);дейсвие
        assertEquals(expected, actual); проверка

        int c = 10;
        int d = 0;
        int result = NumberComparator.compareToInt(c, d);
        assertTrue(result > 0);
} */
    }
    @Test
    @DisplayName("Возвращает -1, когда a < b")
    void compareToIntFirstLess() {
        assertEquals(-1, NumberComparator.compareToInt(3, 5));
        assertTrue(NumberComparator.compareToInt(0, 10) < 0);
    }

    @Test
    @DisplayName("Возвращает 0, когда a == b")
    void compareToIntEqual() {
        assertEquals(0, NumberComparator.compareToInt(5, 5));
        assertEquals(0, NumberComparator.compareToInt(0, 0));
        assertEquals(0, NumberComparator.compareToInt(-10, -10));
    }
}