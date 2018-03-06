package Creators;

import BigNumbers.BigNumber;
import Shapes.IShape;
import Shapes.Circle;
import Shapes.Point;

import java.util.List;

public class CircleCreator implements IShapeCreator {
    private static CircleCreator instance;

    BigNumber R;
    Point center;

    private CircleCreator() {
    }

    @Override
    public IShape create() {
        return new Circle(new Point(new BigNumber("0"), new BigNumber("0")), R);
    }

    @Override
    public void setParameters(List<String> parameters) {
        R = new BigNumber(parameters.get(2));
    }

    public static CircleCreator getInstance() {
        if (instance == null) {
            instance = new CircleCreator();
        }
        return instance;
    }
}