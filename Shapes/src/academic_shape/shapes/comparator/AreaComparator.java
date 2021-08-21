package academic_shape.shapes.comparator;

import academic_shape.shapes.Shape;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {

    public int compare(Shape o1, Shape o2) {
        if (o1.getArea() < o2.getArea()) {
            return 1;
        }
        if (o1.getArea() > o2.getArea()) {
            return -1;
        }
        return 0;
    }
}