package Academic_korol.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        components = new double[dimension];
    }

    public Vector(Vector vector) {
        if (vector.getSize() <= 0) {
            throw new IllegalArgumentException("Размерность вектора n должна быть  > 0 ",);
        }
        components = new double[vector.getSize()];

        for (int i = 0; i < vector.getSize(); i++) {
            components[i] = vector.components[i];
        }
    }

    public Vector(double[] array) {
        if (array.length <= 0) {
            throw new IllegalArgumentException("Размерность вектора n должна быть  > 0 ");
        }
        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int dimension, double[] array) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Размерность вектора n должна быть  > 0 ");
        }

        components = Arrays.copyOf(array, dimension);
    }

    public int getSize() {
        return components.length;
    }

    public double getComponent(int index) {
        return components[index];
    }

    public void setComponent(int index, double number) {
        components[index] = number;
    }

    private void resize(int n) {
        double[] old = components;
        components = new double[n];

        for (int i = 0; i < old.length; i++) {
            components[i] = old[i];
        }

        for (int i = old.length; i < n; i++) {
            components[i] = 0;
        }
    }

    public void add(Vector vector) {
        if (vector.getSize() > components.length) {
            resize(vector.getSize());
        }

        for (int i = 0; i < vector.getSize(); i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector.getSize() > components.length) {
            resize(vector.getSize());
        }

        for (int i = 0; i < vector.getSize(); i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScale(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void negate() {
        multiplyByScale(-1);
    }

    public double getLength() {
        double coordinatesSquaresSum = 0.0;

        for (double e : components) {
            coordinatesSquaresSum += e * e;
        }

        return Math.sqrt(coordinatesSquaresSum);
    }

    @Override
    public String toString() {
        return Arrays.toString(components).replace("[", "{").replace("]", "}");
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector v = (Vector) o;

        if (components.length != v.getSize()) {
            return false;
        }

        for (int i = 0; i < components.length; i++) {

            if (components[i] != v.components[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        int hash = 1;

        for (int i = 0; i < components.length; i++) {
            hash = prime * hash + Double.hashCode(components[i]);
        }

        return hash;
    }

    public static Vector getAdd(Vector vector1, Vector vector2) {

        Vector result = new Vector(vector1);
        result.add(vector2);

        return result;
    }

    public static Vector getSubtract(Vector vector1, Vector vector2) {

        Vector result = new Vector(vector1);
        result.subtract(vector2);

        return result;
    }

    public static double getDot(Vector vector1, Vector vector2) {
        int minVectorSize = Math.min(vector1.getSize(), vector2.getSize());
        double scalarProduct = 0;

        for (int i = 0; i < minVectorSize; i++) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }
}
