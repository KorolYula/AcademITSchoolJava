package AcademITSchoolOOPJava.Shape.shape;

public class Square extends Rectangle {
    //private double sideLength;

    public Square(double sideLength) {
        super(sideLength, sideLength);
    }

    @Override
    public String toString() {
        return String.format("Квадрат со стороной %.2f ",side1Length);
    }
}