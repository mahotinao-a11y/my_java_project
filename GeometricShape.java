
public interface GeometricShape {

    double findSquare();

    double findPerimetr();

    String getColor();

    String getBorderColor();

    default void infoAll() {
        System.out.println("Площадь: " + findSquare());
        System.out.println("Периметр: " + findPerimetr());
        System.out.println("Цвет заливки: " + getColor());
        System.out.println("Цвет границы: " + getBorderColor());
    }
}
