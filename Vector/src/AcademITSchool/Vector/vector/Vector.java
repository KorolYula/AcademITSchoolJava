package AcademITSchool.Vector.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public int getSize() {
        return this.components.length;
    }

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }
        this.components = new double[n];
        for (int i = 0; i < n; i++) {
            this.components[i] = 0;
        }
    }

    public Vector(Vector vector) {
        double[] components = new double[vector.getSize()];
        for (int i = 0; i < vector.getSize(); i++) {
            this.components[i] = vector.components[i];
        }
    }

    public Vector(double[] vector) {
        this.components = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            this.components[i] = vector[i];
        }
    }

    public Vector(int n, double[] vector) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть  > 0");
        }
        this.components = new double[n];
        for (int i = 0; i < vector.length; i++) {
            this.components[i] = vector[i];
        }
        for (int i = vector.length; i < n; i++) {
            this.components[i] = 0;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(this.components).replace("[", "{").replace("]", "}");

    }
}
