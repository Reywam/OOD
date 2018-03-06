package Shapes;

import BigNumbers.BigNumber;

public class Point {
    private BigNumber x;
    private BigNumber y;

    public Point(BigNumber x, BigNumber y) {
        this.x = x;
        this.y = y;
    }

    public BigNumber getX() {
        return x;
    }

    public BigNumber getY() {
        return y;
    }

    public void setX(BigNumber x) {
        this.x = x;
    }

    public void setY(BigNumber y) {
        this.y = y;
    }

    Point sub(Point point) {
        return new Point(this.x.Sub(point.x), this.y.Sub(point.y));
    }
}
