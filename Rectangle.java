
public class Rectangle implements GeometricShape {

    private double width;
    private double height;
    private String fillColor;
    private String borderColor;

    public Rectangle(double width, double height, String fillColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double findSquare() {
        return width * height;
    }

    @Override
    public double findPerimetr() {
        return 2 * (width * height);
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
