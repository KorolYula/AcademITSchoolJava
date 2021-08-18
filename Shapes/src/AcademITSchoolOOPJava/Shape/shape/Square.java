package AcademITSchoolOOPJava.Shape.shape;

public class Square extends Rectangle {

    public Square(double sideLength) {
        super(sideLength, sideLength);
    }

    @Override
    public String toString() {
        return String.format("Квадрат со стороной %.2f ", side1Length);
    }

    @Override
    public boolean equals(Object shape) {
        if (shape == this) return true;
        if (shape == null || shape.getClass() != this.getClass()) return false;
        Square s = (Square) shape;
        return side1Length == s.side1Length;
    }

    public int hashCode() {
        final int prime = 15;
        int hash = 1;
        hash = prime * hash + Double.hashCode(side1Length);
        return hash;
    }
}