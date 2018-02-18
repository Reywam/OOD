package Creators;

import Shapes.IShape;
import Shapes.Point;
import Shapes.Rectangle;

import java.util.List;

public class RectangleCreator implements IShapeCreator {
    private static RectangleCreator instance;

    Point vertex1;
    Point vertex2;

    private RectangleCreator() {
    }

    @Override
    public IShape create() {
        return new Rectangle(vertex1, vertex2);
    }

    @Override
    public void setParameters(List<String> parameters) {
        vertex1 = new Point(Integer.parseInt(parameters.get(0)), Integer.parseInt(parameters.get(1)));
        vertex2 = new Point(Integer.parseInt(parameters.get(2)), Integer.parseInt(parameters.get(3)));
    }

    public static RectangleCreator getInstance() {
        if (instance == null) {
            instance = new RectangleCreator();
        }
        return instance;
    }
}
