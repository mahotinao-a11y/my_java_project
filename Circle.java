
public class Circle implements GeometricShape {

    private double radius;
    private String fillColor;
    private String borderColor;

    public Circle(double radius, String fillColor, String borderColor) {
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double findSquare() {
        return 3.14 * radius * radius;
    }

    @Override
    public double findPerimetr() {
        return 2 * 3.14 * radius;
    }

    @Override
    public String getColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}
