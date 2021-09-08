package academic.korol.shapes.shapes;

public class Square implements Shape {
    private double sideLenght;

    public Square(double sideLenght) {
        this.sideLenght = sideLenght;
    }

    public double getSide() {
        return sideLenght;
    }

    public void setSide(double side) {
        this.sideLenght = sideLenght;
    }

    @Override
    public double getWidth() {
        return sideLenght;
    }

    @Override
    public double getHeight() {
        return sideLenght;
    }

    @Override
    public double getArea() {
        return sideLenght * sideLenght;
    }

    @Override
    public double getPerimeter() {
        return sideLenght * 4;
    }

    @Override
    public String toString() {
        return String.format("Квадрат со стороной %.2f ", sideLenght);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Square s = (Square) o;
        return sideLenght == s.sideLenght;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hash = 1;

        hash = prime * hash + Double.hashCode(sideLenght);

        return hash;
    }
}