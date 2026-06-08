
import java.util.Arrays;

public class Main {
// 1. Создайте метод printThreeWords(), который при вызове должен отпечатать в столбец три слова: Orange, Banana, Apple    

    /*public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");// метод для печати слов
    }
    
    public static void main(String[] args) {
        printThreeWords();  // Вызов метода
    }*/
    // вариант 2
    public static void printThreeWords() { // метод, который печатает слова
        System.out.println("Orange\nBanana\nApple");
    }

// 2. Создайте метод checkSumSign(), в теле которого объявите две int переменные a и b, и инициализируйте их любыми значениями, которыми захотите. 
// Далее метод должен просуммировать эти переменные, и если их сумма больше или равна 0, то вывести в консоль сообщение “Сумма положительная”, в противном случае - “Сумма отрицательная”;
    public static void checkSumSign() { // метод, который проверяет условие
        int a = 10, b = 20;
        if ((a + b) >= 0) {
            System.out.println("Сумма положительная"); // или можно написать int sum = a+b; if (sum>0){.......}
        } else {
            System.out.println("Сумма отрицателная");
        }
    }
// 3.Создайте метод printColor() в теле которого задайте int переменную value и инициализируйте ее любым значением. Если value меньше 0 (0 включительно), 
// то в консоль метод должен вывести сообщение “Красный”, если лежит в пределах от 0 (0 исключительно) до 100 (100 включительно), то “Желтый”, если больше 100 (100 исключительно) - “Зеленый”;

    public static void printColor() {
        int value = 100101;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (0 < value && value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");

        }
    }

// 4. Создайте метод compareNumbers(), в теле которого объявите две int переменные a и b, и инициализируйте их любыми значениями,
// которыми захотите. Если a больше или равно b, то необходимо вывести в консоль сообщение “a >= b”, в противном случае “a < b”
    public static void compareNumbers() {
        int a = 100, b = 230;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    /*public static int methodReturn() {   //этот метод возвращает данные но ничего не печаетает самостоятельно, для печати нужно передать в другой метод System.out.println(methodReturn())
    return 5 + 3;}*/
// 5. Напишите метод, принимающий на вход два целых числа и проверяющий, 
// что их сумма лежит в пределах от 10 до 20 (включительно), если да – вернуть true, в противном случае – false.
    public static boolean sumInt(int a, int b) {// методод принимает в себя значения и проверяет сумму, чтобы получить в консоли вывод, то надо написать System.out.println(sumInt(4,15));
        int sum = (a + b);
        if (10 <= sum && sum <= 20) {
            return true;
        } else {
            return false;
        }
    }

// 6. Напишите метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль, положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.
    public static String positiveOrNegative(int number) {
        if (number >= 0) {
            return "положительное";
        } else {
            return "отрицательное";
        }
    }

// 7. Напишите метод, которому в качестве параметра передается целое число. Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.
// Замечание: ноль считаем положительным числом.
    public static boolean isNagative(int number) {

        if (number < 0) {
            return true;
        } else if (number == 0) {
            return false;
        } else {
            return false;
        }
    }

// 8. Напишите метод, которому в качестве аргументов передается строка и число, метод должен отпечатать в консоль указанную строку, указанное количество раз;
    public static void textNtimes(String str, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

// 9. Напишите метод, который определяет, является ли год високосным, и возвращает boolean (високосный - true, 
// не високосный - false). Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
    public static boolean isYearLeap(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else if (year % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }
// 10. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;

    public static void Number() {
        int[] number = {1, 1, 1, 0, 0, 1, 0, 0};
        for (int i = 0; i < number.length; i++) {
            number[i] = (number[i] == 0) ? 1 : 0;
        }
        System.out.println(Arrays.toString(number));
    }

// 11. Задать пустой целочисленный массив длиной 100. С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 ... 100;
    public static void fillArrayNumbers() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        System.out.println(Arrays.toString(array));
    }
// 12. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;

    public static void lessThanSix() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        System.out.println(Arrays.toString(array)); // печать массива до изменений

        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] = array[i] * 2;
            }
        }

        System.out.println(Arrays.toString(array)); // печать массива после изменений
    }
// 13. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами (можно только одну из диагоналей, если обе сложно). Определить элементы одной из диагоналей можно по следующему принципу: индексы таких элементов равны, то есть [0][0], [1][1], [2][2], ..., [n][n];

// 14. Написать метод, принимающий на вход два аргумента: len и initialValue, и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue.
    public static void main(String[] args) {// вызов метода main для входа в программу 
        System.out.println("Задание 1");
        printThreeWords(); // вызов метода отображения печати слов
        System.out.println("Задание 2");
        checkSumSign(); //вызов метода для отображения суммы проверки условия
        System.out.println("Задание 3");
        printColor();
        /*System.out.println(methodReturn());*/ // медод для вычисления и печати результата
        System.out.println("Задание 4");
        compareNumbers();
        System.out.println("Задание 5");
        System.out.println(sumInt(4, 15));// печать в консоли ответа
        System.out.println(sumInt(412, 15));
        System.out.println("Задание 6");
        System.out.println(positiveOrNegative(-1));
        System.out.println("Задание 7");
        System.out.println(isNagative(9)); // если в коде есть return, то печать метода из main всегда
        System.out.println(isNagative(0));
        System.out.println(isNagative(-2));
        System.out.println("Задание 8");
        textNtimes("hello", 10);
        System.out.println("Задание 9");
        System.out.println(isYearLeap(2010));
        System.out.println(isYearLeap(2013));
        System.out.println(isYearLeap(2017));
        System.out.println(isYearLeap(2008));
        System.out.println(isYearLeap(2015));
        System.out.println("Задание 10");
        Number();
        System.out.println("Задание 11");
        fillArrayNumbers();
        System.out.println("Задание 12");
        lessThanSix();
        System.out.println("Задание 13");
        System.out.println("Задание 14");

    }
}
