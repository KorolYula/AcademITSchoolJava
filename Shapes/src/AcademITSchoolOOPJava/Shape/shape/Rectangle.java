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

    @Override
    public boolean equals(Object shape) {
        if (shape == this) {
            return true;
        }
        if (shape == null || shape.getClass() != this.getClass()) {
            return false;
        }
        Rectangle r = (Rectangle) shape;
        return side1Length == r.side1Length && side2Length == r.side2Length;
    }

    public int hashCode() {
        final int prime = 15;
        int hash = 1;
        hash = prime * hash + Double.hashCode(side1Length);
        hash = prime * hash + Double.hashCode(side2Length);
        return hash;
    }
}
