package AcademItSchool.Matrix.matrix;

import Academic_korol.vector.Vector;


public class Matrix {
    private Vector[] rows;

    public int getRowsCount() {
        return this.rows.length;
    }

    public int getColumnsCount() {
        return this.rows[0].getSize();
    }

    public Matrix(int n, int m) {
        this.rows = new Vector[n];
        for (int i = 0; i < n; i++) {
            this.rows[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        this.rows = new Vector[matrix.getRowsCount()];

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            this.rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        //TODO Проверка , что все вектора одной длины,добить нулями
        this.rows = new Vector[vectorsArray.length];

        for (int i = 0; i < vectorsArray.length; i++) {
            this.rows[i] = new Vector(vectorsArray[i]);
        }
    }

    //TODO конструктор из массива проверка размерности
    public Matrix(double[][] array) {
        this.rows = new Vector[array.length];// размерность по строкам
        for (int i = 0; i < array.length; i++) {
            this.rows[i] = new Vector(array[i]);
        }
    }

    public Vector getRowVector(int i) {
        if (i > this.getRowsCount()) {
            throw new IllegalArgumentException("Выход за пределы матрицы");
        }
        return new Vector(this.rows[i]);
    }

    public void setRowVector(int i, Vector vector) {
        if (vector.getSize() > this.getColumnsCount()) {
            throw new IllegalArgumentException("Размерность вектора больше размерности матрицы");
        }
        if (vector.getSize() <= this.getColumnsCount()) {

            for (int j = 0; j < vector.getSize(); j++) {
                this.rows[i].setComponent(j, vector.getComponent(j));
            }
            for (int j = vector.getSize(); j < this.getColumnsCount(); j++) {
                this.rows[i].setComponent(j, 0);
            }
        }
    }

    public Vector getColumnVector(int i) {
        if (i > this.getColumnsCount()) {
            throw new IllegalArgumentException("Выход за пределы матрицы");
        }
        Vector v = new Vector(this.getRowsCount());

        for (int j = 0; j < getRowsCount(); j++) {
            v.setComponent(j, this.getRowVector(j).getComponent(i));
        }
        return v;
    }

    public void scale(double scalar) {
        for (int i = 0; i < this.getRowsCount(); i++) {
            this.rows[i].scale(scalar);
        }
    }


    public Matrix getTransposed() {
        Matrix matrix = new Matrix(this.getColumnsCount(), this.getRowsCount());

        for (int i = 0; i < this.getColumnsCount(); i++) {
            matrix.setRowVector(i, this.getColumnVector(i));
        }
        return matrix;
    }


    //TODO определитель матрицы

    //TODO toString определить так, чтобы результат получался в таком виде: { { 1, 2 }, { 2, 3 } }

    //TODO умножение матрицы на вектор


    public void add(Matrix matrix) {
        if (matrix.getRowsCount() != this.getRowsCount()) {
            throw new IllegalArgumentException("Размерности матриц не совпадают!");
        }

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            this.setRowVector(i, Vector.add(this.getRowVector(i), matrix.getRowVector(i)));
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix.getRowsCount() != this.getRowsCount()) {
            throw new IllegalArgumentException("Размерности матриц не совпадают!");
        }

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            this.setRowVector(i, Vector.subtract(this.getRowVector(i), matrix.getRowVector(i)));
        }
    }
}

// TODO Статические методы:Сложение матриц

// TODO Статические методы Вычитание матриц

//TODO Статические методы Умножение матриц




