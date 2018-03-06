package Shapes;

import BigNumbers.BigNumber;

public interface IShape {
    BigNumber calculateArea();
    BigNumber calculatePerimeter();
    String getName();
}
