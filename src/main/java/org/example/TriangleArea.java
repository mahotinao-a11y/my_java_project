package org.example;

public class TriangleArea {
    public static double calculateSide(double a, double h) {
        if (a <= 0 || h <= 0) {
            throw new IllegalArgumentException("Основание и высота должны быть положительными");
        }
            return (a * h) / 2;
    }
}


