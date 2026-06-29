package org.example;// объявление пакета, адрес где лежит файл

public class NumberComparator {// основной код программы, логика

    public static String compare(int a, int b) { // метод обязательно static, принадлежит классу а не объекту
        if (a > b) {
            return a + " больше " + b;
        } else if (a < b) {
            return a + " меньше " + b;
        } else {
            return a + " равно " + b;
        }
    }
    public static int compareToInt(int a, int b) {
        return Integer.compare(a, b);// встроенны метод джава для сравнения
    }
    /*public static int compareToInt(int a, int b) {
    if (a > b) {
        return 1;
    } else if (a < b) {
        return -1;
    } else {
        return 0;
    } или можно вот так написать, но лучше использовать встроенный метод Integer*/
}