package Creators;
import Shapes.IShape;

import java.util.List;

public interface IShapeCreator {
    IShape create();
    void setParameters(List<String> parameters);
}
