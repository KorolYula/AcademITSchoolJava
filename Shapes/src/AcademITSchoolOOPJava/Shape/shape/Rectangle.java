package AcademITSchoolOOPJava.Shape.shape;

public class Rectangle implements Shape,Comparable<Rectangle> {
    private double side1Length;
    private double side2Length;

    public Rectangle(double side1Length,double side2Length) {
        this.side1Length = side1Length;
        this.side2Length = side2Length;
    }
    public double getWidth() {
        return side1Length;
    }

    public double getHeight() {
        return side2Length;
    }

    public double getArea() {
        return side1Length * side2Length;
    }

    public double getPerimeter() {
        return (side1Length+side2Length) * 2;
    }
    @Override
    public int compareTo(Rectangle rectangle) {
        if (getArea() == rectangle.getArea()) {
            return 0;
        }
        if (getArea() > rectangle.getArea()) {
            return 1;
        }
        return -1;
    }
}
