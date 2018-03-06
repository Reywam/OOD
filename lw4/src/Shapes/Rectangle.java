package Shapes;

import BigNumbers.BigNumber;

public class Rectangle implements IShape {

    private Point vertex1;
    private Point vertex2;
    private static String name = "Rectangle";

    public Rectangle(Point vertex1, Point vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    @Override
    public BigNumber calculateArea() {
        BigNumber value = vertex1.getX().Sub(vertex2.getX()).Multiply(vertex1.getY().Sub(vertex2.getY()));
        if(!value.isPositive()) {
            value.Multiply(new BigNumber("-1"));
        }
        return value;
    }

    @Override
    public BigNumber calculatePerimeter() {
        Point vect1 = new Point(vertex2.getX().Sub(vertex1.getX()), vertex1.getY());
        Point vect2 = new Point(vertex1.getX(), vertex2.getY().Sub(vertex1.getY()));

        BigNumber width = (vect2.getY().Sub(vect1.getY()));
        if(!width.isPositive()) {
            width.Multiply(new BigNumber("-1"));
        }
        BigNumber height = (vect2.getX().Sub(vect1.getX()));
        if(!height.isPositive()) {
            width.Multiply(new BigNumber("-1"));
        }
        BigNumber val = new BigNumber("2").Multiply(width.Add(height));
        return val;
    }

    public void setFirstVertex(Point vertex) {
         vertex1 = vertex;
    }

    public void setSecondVertex(Point vertex) {
        vertex2 = vertex;
    }

    @Override
    public String getName() {
        return name;
    }
}