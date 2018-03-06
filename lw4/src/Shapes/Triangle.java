package Shapes;

import BigNumbers.BigNumber;

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
    public BigNumber calculateArea() {
        BigNumber p = vertex2.getX().Sub(vertex1.getX());
        BigNumber s = vertex3.getY().Sub(vertex1.getY());
        BigNumber q = vertex3.getX().Sub(vertex1.getX());
        BigNumber r = vertex2.getY().Sub(vertex1.getY());
        BigNumber firstArg = p.Multiply(s);
        BigNumber secondArg = r.Multiply(q);
        //BigNumber d = Math.abs(firstArg.Sub(secondArg));
        BigNumber d = firstArg.Sub(secondArg);
        if(!d.isPositive()) {
            d = d.Multiply(new BigNumber("-1"));
        }
        return d.Divide(new BigNumber("2"));
    }

    @Override
    public BigNumber calculatePerimeter() {
        Point vect1 = new Point(vertex2.getX().Sub(vertex1.getX()), vertex2.getY().Sub(vertex1.getY()));
        Point vect2 = new Point(vertex2.getX().Sub(vertex3.getX()), vertex2.getY().Sub(vertex3.getY()));
        Point vect3 = new Point(vertex3.getX().Sub(vertex1.getX()), vertex3.getY().Sub(vertex1.getY()));

        BigNumber vect1Size = (vect1.getX().Multiply(vect1.getX()).Add(vect1.getY().Multiply(vect1.getY()))).Sqrt();
        BigNumber vect2Size = (vect2.getX().Multiply(vect2.getX()).Add(vect2.getY().Multiply(vect2.getY()))).Sqrt();
        BigNumber vect3Size = (vect3.getX().Multiply(vect3.getX()).Add(vect3.getY().Multiply(vect3.getY()))).Sqrt();

        return (vect1Size.Add(vect2Size).Add(vect3Size));
    }

    @Override
    public String getName() {
        return name;
    }
}