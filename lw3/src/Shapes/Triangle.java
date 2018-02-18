package Shapes;

public class Triangle implements IShape {
    private Point vertex1;
    private Point vertex2;
    private Point vertex3;

    private static String name = "Triangle";

    public Triangle(Point vertex1, Point vertex2, Point vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    @Override
    public double calculateArea() {
        int p = vertex2.getX() - vertex1.getX();
        int s = vertex3.getY() - vertex1.getY();
        int q = vertex3.getX() - vertex1.getX();
        int r = vertex2.getY() - vertex1.getY();
        double d = Math.abs(p * s - r * q);

        return d / 2;
    }

    @Override
    public int calculatePerimeter() {
        Point vect1 = new Point(vertex2.getX() - vertex1.getX(), vertex2.getY() - vertex1.getY());
        Point vect2 = new Point(vertex2.getX() - vertex3.getX(), vertex2.getY() - vertex3.getY());
        Point vect3 = new Point(vertex3.getX() - vertex1.getX(), vertex3.getY() - vertex1.getY());

        double vect1Size = Math.sqrt(vect1.getX() * vect1.getX() + vect1.getY() * vect1.getY());
        double vect2Size = Math.sqrt(vect2.getX() * vect2.getX() + vect2.getY() * vect2.getY());
        double vect3Size = Math.sqrt(vect3.getX() * vect3.getX() + vect3.getY() * vect3.getY());

        return (int) (vect1Size + vect2Size + vect3Size);
    }

    @Override
    public String getName() {
        return name;
    }
}