package academic_shape.shapes.comparator;

import academic_shape.shapes.Shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {

    @Override
    public int compare(Shape o1, Shape o2) {
        if (o1.getPerimeter() < o2.getPerimeter()) {
            return 1;
        }
        if (o1.getPerimeter() > o2.getPerimeter()) {
            return -1;
        }
        return 0;
    }
}

