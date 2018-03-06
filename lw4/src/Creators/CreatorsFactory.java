package Creators;

import Shapes.IShape;

import java.util.List;

public class CreatorsFactory {
    public IShapeCreator create(String shape, List<String> parameters){
        IShapeCreator creator = null;
        switch (shape) {
            case "CIRCLE":
                creator = CircleCreator.getInstance();
                break;
            case "TRIANGLE":
                creator = TriangleCreator.getInstance();
                break;
            case "RECTANGLE":
                creator = RectangleCreator.getInstance();
                break;
        }
        creator.setParameters(parameters);
        return creator;
    }
}
