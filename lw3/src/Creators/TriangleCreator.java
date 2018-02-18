package Creators;

import Shapes.IShape;
import Shapes.Point;
import Shapes.Triangle;

import java.util.List;

public class TriangleCreator implements IShapeCreator {
    private static TriangleCreator instance;

    Point vertex1 = new Point(0,0);
    Point vertex2 = new Point(0,0);
    Point vertex3 = new Point(0,0);

    private TriangleCreator() {
    }

    @Override
    public IShape create() {
        return new Triangle(vertex1, vertex2, vertex3);
    }

    @Override
    public void setParameters(List<String> parameters) {
        vertex1 = new Point(Integer.parseInt(parameters.get(0)), Integer.parseInt(parameters.get(1)));
        vertex2 = new Point(Integer.parseInt(parameters.get(2)), Integer.parseInt(parameters.get(3)));
        vertex3 = new Point(Integer.parseInt(parameters.get(4)), Integer.parseInt(parameters.get(5)));
    }

    public static TriangleCreator getInstance() {
        if (instance == null) {
            instance = new TriangleCreator();
        }
        return instance;
    }
}
