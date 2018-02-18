package Shapes;

public class Circle implements IShape {
    Point center;
    int R;
    private static String name = "Circle";

    public Circle(Point center, int R) {
        this.center = center;
        this.R = R;
    }

    @Override
    public double calculateArea() {
        double val = Math.PI * R * R;
        return val;
    }

    @Override
    public int calculatePerimeter() {
        return (int) (R * 2 * Math.PI);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setRadius(int R) {
        this.R = R;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

}