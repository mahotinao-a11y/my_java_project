
public class Main {

    public static void createArray(String[][] array) { // метод для вывода массива 4 на 4
        System.out.println("Массив 4 на 4");
        for (int i = 0; i < 4; i++) { // цикл внешний 
            for (int j = 0; j < 4; j++) { // цикл внутренний
                System.out.print(array[i][j] + " "); // вывод каджого элемента массива
            }
        System.out.println(); // перевод каждой строчки на новую строку
        }
    }
    public static void main(String[] args) { // вход в программу для запуска кода
        String[][] myArray = {// массив для заполнения значений массива
            {"E", "F", "G", "H"},
            {"E", "F", "G", "H"},
            {"I", "J", "K", "L"},
            {"M", "N", "O", "P"}
        };

        createArray(myArray); // вызоа метода

    }
    }
    
    
