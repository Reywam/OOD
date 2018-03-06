package Shapes;

import BigNumbers.BigNumber;

public class Circle implements IShape {
    Point center;
    BigNumber R;
    private static String name = "Circle";
    final BigNumber PI = new BigNumber("314");

    public Circle(Point center, BigNumber R) {
        this.center = center;
        this.R = R;
    }

    @Override
    public BigNumber calculateArea() {
        BigNumber sqrR = R.Multiply(R);
        BigNumber val = PI.Multiply(sqrR).Divide(new BigNumber("100"));
        return val;
    }

    @Override
    public BigNumber calculatePerimeter() {
        // 2 * R * PI
        BigNumber value = R.Multiply(new BigNumber("2")).Multiply(PI).Divide(new BigNumber("100"));
        return value;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setRadius(BigNumber R) {
        this.R = R;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

}