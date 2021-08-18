package AcademITSchool.Vector.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public int getSize() {
        return this.components.length;
    }

    public double getComponent(int i) {
        return this.components[i];
    }

    public void setComponent(int i,double number) {
        this.components[i]=number;
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

    public Vector(double[] array) {
        this.components = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            this.components[i] = array[i];
        }
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть  > 0");
        }

        this.components = new double[n];

        for (int i = 0; i < array.length; i++) {
            this.components[i] = array[i];
        }

        for (int i = array.length; i < n; i++) {
            this.components[i] = 0;
        }
    }

    public void addAnotherVector(Vector vector) {
        if (vector.getSize() < this.getSize()) {
            for (int i = vector.getSize(); i < this.getSize(); i++) {
                vector.components[i] = 0;
            }
        }

        if (vector.getSize() > this.getSize()) {
            for (int i = this.getSize(); i < vector.getSize(); i++) {
                this.components[i] = 0;
            }
        }

        for (int i = 0; i < vector.getSize(); i++) {
            this.components[i] += vector.components[i];
        }
    }


    public void subtractAnotherVector(Vector vector) {
        if (vector.getSize() < this.getSize()) {
            for (int i = vector.getSize(); i < this.getSize(); i++) {
                vector.components[i] = 0;
            }
        }

        if (vector.getSize() > this.getSize()) {
            for (int i = this.getSize(); i < vector.getSize(); i++) {
                this.components[i] = 0;
            }
        }

        for (int i = 0; i < vector.getSize(); i++) {
            this.components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(int scalar) {
        for (int i = 0; i < this.getSize(); i++) {
            this.components[i] *= scalar;
        }
    }

    public void unfoldVector() {
        this.multiplyByScalar(-1);
    }

    public double getVectorLength() {
        int squaresCoordinatesSum = 0;

        for (int i = 0; i < this.getSize(); i++) {
            squaresCoordinatesSum += this.components[i] * this.components[i];
        }

        return Math.sqrt(squaresCoordinatesSum);
    }


    @Override
    public String toString() {
        return Arrays.toString(this.components).replace("[", "{").replace("]", "}");

    }

    @Override
    public boolean equals(Object vector) {
        if (vector == this) {
            return true;
        }

        if (vector == null || vector.getClass() != this.getClass()) {
            return false;
        }

        Vector v = (Vector) vector;

        if (this.getSize() != v.getSize()) {
            return false;
        }

        boolean isEqequals = true;

        for (int i = 0; i < this.getSize(); i++) {

            if (this.components[i] != v.components[i]) {
                isEqequals = false;
            }
        }

        return isEqequals;
    }

    @Override
    public int hashCode() {
        final int prime = 15;
        int hash = 1;

        for (int i = 0; i < this.getSize(); i++) {
            hash = prime * hash + Double.hashCode(this.components[i]);
        }
        return hash;
    }
}
