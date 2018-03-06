package Creators;

import BigNumbers.BigNumber;
import Shapes.IShape;
import Shapes.Point;
import Shapes.Triangle;

import java.util.List;

public class TriangleCreator implements IShapeCreator {
    private static TriangleCreator instance;

    Point vertex1 = new Point(new BigNumber("0"), new BigNumber("0"));
    Point vertex2 = new Point(new BigNumber("0"), new BigNumber("0"));
    Point vertex3 = new Point(new BigNumber("0"), new BigNumber("0"));

    private TriangleCreator() {
    }

    @Override
    public IShape create() {
        return new Triangle(vertex1, vertex2, vertex3);
    }

    @Override
    public void setParameters(List<String> parameters) {
        vertex1 = new Point(new BigNumber(parameters.get(0)), new BigNumber(parameters.get(1)));
        vertex2 = new Point(new BigNumber(parameters.get(2)), new BigNumber(parameters.get(3)));
        vertex3 = new Point(new BigNumber(parameters.get(4)), new BigNumber(parameters.get(5)));
    }

    public static TriangleCreator getInstance() {
        if (instance == null) {
            instance = new TriangleCreator();
        }
        return instance;
    }
}
