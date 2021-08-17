package AcademITSchoolOOPJava.Shape.shape;

public class Circle implements Shape, Comparable<Circle> {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return radius * 2;
    }

    public double getHeight() {
        return radius * 2;
    }

    public double getArea() {
        return radius * radius * Math.PI;
    }

    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    @Override
    public int compareTo(Circle circle) {
        if (getArea() == circle.getArea()) {
            return 0;
        }
        if (getArea() > circle.getArea()) {
            return 1;
        }
        return -1;
    }
    @Override
    public String toString() {
        return String.format("Круг с радиусом %.2f", radius);
    }
}
