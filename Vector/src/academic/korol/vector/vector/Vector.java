package academic.korol.vector.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0, а сейчас " + size);
        }

        components = new double[size];
    }

    public Vector(Vector v) {
        if (v.components.length == 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0, а сейчас " + v.components.length);
        }

        components = Arrays.copyOf(v.components, v.components.length);
    }

    public Vector(int size, Vector vector) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0, а сейчас " + size);
        }

        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0, а сейчас " + array.length);
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0, а сейчас " + size);
        }

        components = Arrays.copyOf(array, size);
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

    private void resize(int newSize) {
        components = Arrays.copyOf(components, newSize);
    }

    public void add(Vector vector) {
        if (vector.components.length > components.length) {
            resize(vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector.components.length > components.length) {
            resize(vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double squaresSum = 0.0;

        for (double e : components) {
            squaresSum += e * e;
        }

        return Math.sqrt(squaresSum);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (double e : components) {
            stringBuilder.append(e);
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
        stringBuilder.append("}");

        return stringBuilder.toString();
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

        if (components.length != v.components.length) {
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
        return Arrays.hashCode(components);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.add(vector2);

        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.subtract(vector2);

        return result;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        int minVectorSize = Math.min(vector1.components.length, vector2.components.length);
        double scalarProduct = 0;

        for (int i = 0; i < minVectorSize; i++) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }
}
