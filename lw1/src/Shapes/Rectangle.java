package Shapes;

public class Rectangle implements IShape {

    private Point vertex1;
    private Point vertex2;
    private static String name = "Rectangle";

    public Rectangle(Point vertex1, Point vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    @Override
    public double calculateArea() {
        return (Math.abs(vertex1.getX() - vertex2.getX()) * (Math.abs(vertex1.getY() - vertex2.getY())));
    }

    @Override
    public int calculatePerimeter() {
        Point vect1 = new Point(vertex2.getX() - vertex1.getX(), vertex1.getY());
        Point vect2 = new Point(vertex1.getX(), vertex2.getY() - vertex1.getY());

        int width = Math.abs(vect2.getY() - vect1.getY());
        int height = Math.abs(vect2.getX() - vect1.getX());

        int val = 2 * Math.abs(width + height);
        return val;
    }

    @Override
    public String getName() {
        return name;
    }
}