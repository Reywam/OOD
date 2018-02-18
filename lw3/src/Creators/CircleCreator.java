package Creators;

import Shapes.IShape;
import Shapes.Circle;
import Shapes.Point;

import java.util.List;

public class CircleCreator implements IShapeCreator {
    private static CircleCreator instance;

    int R;
    Point center;

    private CircleCreator() {
    }

    @Override
    public IShape create() {
        return new Circle(new Point(0, 0), R);
    }

    @Override
    public void setParameters(List<String> parameters) {
        R = Integer.parseInt(parameters.get(2));
    }

    public static CircleCreator getInstance() {
        if (instance == null) {
            instance = new CircleCreator();
        }
        return instance;
    }
}