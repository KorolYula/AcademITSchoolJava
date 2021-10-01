package academic.korol.hash_table;

import java.util.Arrays;

import academic.korol.shapes.shapes.Circle;
import academic.korol.shapes.shapes.Shape;
import academic.korol.shapes.shapes.Square;

public class Main {
    public static void main(String[] args) {
        MyHashTable<academic.korol.shapes.shapes.Shape> hashTableShapes = new MyHashTable<>();
        Shape shape1 = new Square(5);
        Shape shape2 = new Circle(13);
        Shape shape3 = new Circle(34);
        Shape shape4 = new Circle(45);

        hashTableShapes.add(shape2);
        hashTableShapes.add(shape1);
        hashTableShapes.add(shape3);
        hashTableShapes.add(shape4);

        System.out.println(Arrays.toString(hashTableShapes.toStringHashTable()));
        System.out.println(Arrays.toString(hashTableShapes.toArray()));
    }
}