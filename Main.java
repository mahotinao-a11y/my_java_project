
public class Main {

    public static void main(String[] args) {
        Circle circle = new Circle(5, "Желтый", "Красный");
        System.out.println("Круг");
        circle.infoAll();
        Rectangle rectangle = new Rectangle(4, 8, "Синий", " Фиолетовый");
        System.out.println("Прямоугольник");
        rectangle.infoAll();
        Triangle triangle = new Triangle(3.0, 4.0, 5.0, "Зеленый", "Синий");
        System.out.println("Треугольник");
        triangle.infoAll();
    }
}
