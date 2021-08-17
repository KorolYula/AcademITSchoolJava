package AcademITSchoolOOPJava.Shape.shape;

public class Rectangle implements Shape {
    protected double side1Length;
    private double side2Length;

    public Rectangle(double side1Length, double side2Length) {
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
        return (side1Length + side2Length) * 2;
    }

    @Override
    public String toString() {
        return String.format("Прямоугольник со сторонами %.2f и  %.2f", side1Length, side2Length);
    }
}
