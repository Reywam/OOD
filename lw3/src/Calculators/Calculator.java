package Calculators;

import Shapes.IShape;

public class Calculator implements ICalculator{

    @Override
    public String calculatePerimeter(IShape shape) {
        return String.valueOf(shape.calculatePerimeter());
    }

    @Override
    public String calculateArea(IShape shape) {
        return String.valueOf(shape.calculateArea());
    }
}
