package academic_shape.shapes_main;

import academic_shape.shapes.*;
import academic_shape.shapes.comparator.AreaComparator;
import academic_shape.shapes.comparator.PerimeterComparator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(3),
                new Triangle(3, 1, 4, 5, 1, 0),
                new Triangle(0, 3, 4, 0, 7, 0),
                new Circle(3),
                new Rectangle(3, 2),
                new Square(6),
                new Square(5),
                new Square(2),
                new Circle(13),
                new Rectangle(1, 6)};

        Arrays.sort(shapes, new AreaComparator());
        System.out.print("Фигура с максимальной площадью: ");
        System.out.println(shapes[0]);

        Arrays.sort(shapes, new PerimeterComparator());
        System.out.print("Фигура со вторым по величине периметром: ");
        System.out.println(shapes[1]);
    }
}
