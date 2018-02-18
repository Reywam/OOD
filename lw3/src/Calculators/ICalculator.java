package Calculators;

import Shapes.IShape;

public interface ICalculator {
    String calculatePerimeter(IShape shape);
    String calculateArea(IShape shape);
}
