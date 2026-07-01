package org.example;

public class Calculator {

    // Сложение
    public static int summ(int a, int b) {
        return a + b;
    }

    // Вычитание
    public static int subtract(int a, int b) {
        return a - b;
    }

    // Умножение
    public static int multiply(int a, int b) {
        return a * b;
    }

    // Деление
    public static double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Деление на ноль невозможно");
        }
        return (double) a / b;
    }
}
