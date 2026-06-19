
class MyArraySizeException
        extends RuntimeException { // создаем класс исключения, чтобы в последсвии понимать что за ошибка

    public MyArraySizeException(String message1) { // коструктор класса
        super(message1);
    }
}

class MyArrayDataException extends RuntimeException {// класс исключения, при выявление неверного формата элемента

    public MyArrayDataException(String massage2) {
        super(massage2);
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
        for (int i = 0; i < 4; i++) {// задаем длину массива
            for (int j = 0; j < 4; j++) {
                System.out.print(array[i][j] + " ");// вывод каждой строки массива
            }
            System.out.println();// перевод строки массива на другую новую строчку
        }

        int sum = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    int number = Integer.parseInt(array[i][j]); // метод преобразует строку в число
                    sum += number;// прибавка каждого элемента к общей сумму

                } catch (NumberFormatException e) {//поймать ошибку если формат не числовой
                    throw new MyArrayDataException("Элемент массива -" + array[i][j] + " в ячейке[" + i + "][" + j + "]имеет не числовой формат");
                }
            }
        }
        System.out.println("Сумма всех элементов массива: " + sum);
    }

    public static void main(String[] args) {
        String[][] incorrectArray = { // задаем массив
            {"1", "2", "3", "4"},
            {"1", "2", "3", "4"},
            {"1", "ф", "3", "4"},
            {"1", "2", "3", "4"}
        };
        try {
            createArray(incorrectArray);

        } catch (MyArraySizeException e) {
            System.out.println("Ошибка:" + e.getMessage()); //вывод исключения , если массив имеет отличный формат от 4 на 4
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка:" + e.getMessage());// вывод исключение, если в массиве есть хотя бы один не число
        }
    }
}
