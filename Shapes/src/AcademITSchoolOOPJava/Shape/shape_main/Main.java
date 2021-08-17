package AcademITSchoolOOPJava.Shape.shape_main;

import AcademITSchoolOOPJava.Shape.shape.*;

import java.util.*;

public class Main {
    public static  Shape getMaxAreaShape(ArrayList<Shape> shapes, int k ) {
        Comparator<Shape> comparator = new Comparator<Shape>() {
            public int compare(Shape s1, Shape s2) {
                return -Double.compare(s1.getArea(), s2.getArea());
            }
        };
        Collections.sort(shapes, comparator);

        return shapes.get(k);
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        ArrayList<Shape> shapes = new ArrayList<Shape>();
        shapes.add(new Square(3));
        shapes.add(new Triangle(3,1,4,5,1,0));
        shapes.add(new Triangle(0,3,4,0,7,0));
        shapes.add(new Circle(3));
        shapes.add(new Rectangle(3,2));
        shapes.add(new Square(6));
        shapes.add(new Circle(5));
        shapes.add(new Square(2));
        shapes.add(new Circle(13));
        shapes.add(new Rectangle(1,6));

        System.out.print("Фигура с максимальной площадью: ");
        System.out.println(getMaxAreaShape(shapes,0));

        System.out.print("Фигура со второй по величине площадью: ");
        System.out.println(getMaxAreaShape(shapes,1));
    }
}
