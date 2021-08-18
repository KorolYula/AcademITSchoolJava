package AcademITSchoolOOPJava.Shape.shape;

public class Circle implements Shape {
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
    public String toString() {
        return String.format("Круг с радиусом %.2f", radius);
    }

    @Override
    public boolean equals(Object shape) {
        if (shape == this) return true;
        if (shape == null || shape.getClass() != this.getClass()) return false;
        Circle c = (Circle) shape;
        return radius == c.radius;
    }

    public int hashCode() {
        final int prime = 15;
        int hash = 1;
        hash = prime * hash + Double.hashCode(radius);
        return hash;
    }
}

