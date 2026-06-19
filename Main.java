
class MyArraySizeException extends RuntimeException { // создаем класс исключения, чтобы в последсвии понимать что за ошибка

    public MyArraySizeException(String message) { // коструктор класса
        super(message);
    }
}

public class Main {

    public static void createArray(String[][] array) { // метод, который вызываем в конце
        if (array.length != 4) { // общаяя проверка, что в массисе 4 строки
            throw new MyArraySizeException("Массив содержит невернное количество строк" + array.length);
        }
        for (int i = 0; i < array.length; i++) {//проходим по каждому стороке массива
            if (array[i].length != 4) {// проверяем длину каждой строчки, проврке по j не нужна, так j -'это элемент в столбце массива, i показывает длину строчки массива и заодно сколько колонок будет
                throw new MyArraySizeException("Неверное количество столбцов: " + i + "имеет длину " + array[i].length);
            }
        }
        System.out.println("Массив 4 на 4");
        for (int i = 0; i < 4; i++) {// задаем ддлину массива
            for (int j = 0; j < 4; j++) {
                System.out.print(array[i][j] + " ");// вывод каждой строки массива
            }
            System.out.println();// первод новой строки массива на другую строчку
        }
    }

    public static void main(String[] args) {
        String[][] rightArray = new String[4][4];//массив правильной длины
        char letter = 'A'; //задаем начальный элемент массива
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                rightArray[i][j] = String.valueOf(letter++); // увеличение длины массива на 1 с кадждым проходом по циклам и перевод массива в строку
            }
        }
        createArray(rightArray);
    }
}
