package academic_shape.shapes;

public class Square implements Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double getWidth() {
        return side;
    }

    @Override
    public double getHeight() {
        return side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return side * 4;
    }

    @Override
    public String toString() {
        return String.format("Квадрат со стороной %.2f ", side);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != getClass()){
            return false;
        }

        Square s = (Square) o;
        return side == s.side;
    }

    @Override
    public int hashCode() {
        final int prime = 15;
        int hash = 1;

        hash = prime * hash + Double.hashCode(side);

        return hash;
    }
}