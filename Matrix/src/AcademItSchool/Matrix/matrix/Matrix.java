package AcademItSchool.Matrix.matrix;

import Academic_korol.vector.Vector;


public class Matrix {
    private Vector[] rows;

        public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Размеры матрицы не могут быть <0" + n + m);
        }
        rows = new Vector[n];
        for (int i = 0; i < n; i++) {
            rows[i] = new Vector(m);
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
        int maxSize = vectorsArray.length;
        for (Vector e : vectorsArray) {
            maxSize = Math.max(maxSize, e.getSize());
        }

        rows = new Vector[vectorsArray.length];
        for (int i = 0; i < vectorsArray.length; i++) {
            rows[i] = new Vector(vectorsArray[i]);

        }

    }

    //TODO конструктор из массива проверка размерности
    public Matrix(double[][] array) {
        int maxSize = array.length;
        for (double[] e : array) {
            maxSize = Math.max(maxSize, e.length);
        }
        rows = new Vector[maxSize];
        System.arraycopy(array, 0, rows, 0, maxSize);
        /*for (int i = 0; i < array.length; i++) {
            this.rows[i] = new Vector(array[i]);
        }*/
    }

    public int getRowsCount() {
        return this.rows.length;
    }

    public int getColumnsCount() {
        return this.rows[0].getSize();
    }

    public Vector getRowVector(int i) {
        if (i > getRowsCount()) {
            throw new IllegalArgumentException("Выход за пределы матрицы");
        }
        return new Vector(rows[i]);
    }

    public void setRowVector(int i, Vector vector) {
        if (vector.getSize() > getColumnsCount()) {
            throw new IllegalArgumentException("Размерность вектора больше размерности матрицы");
        }
        if (vector.getSize() <= getColumnsCount()) {

            System.arraycopy(vector, 0, rows, 0, getColumnsCount());
            /*for (int j = 0; j < vector.getSize(); j++) {
                rows[i].setComponent(j, vector.getComponent(j));
            }
            for (int j = vector.getSize(); j < getColumnsCount(); j++) {
                rows[i].setComponent(j, 0);
            }*/
        }
    }

    public Vector getColumnVector(int i) {
        if (i > this.getColumnsCount()) {
            throw new IllegalArgumentException("Выход за пределы матрицы");
        }
        Vector v = new Vector(this.getRowsCount());

        for (int j = 0; j < getRowsCount(); j++) {
            v.setComponent(j, getRowVector(j).getComponent(i));
        }
        return v;
    }

    public void scale(double scalar) {
        for (int i = 0; i < this.getRowsCount(); i++) {
            rows[i].multiplyByScale(scalar);
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

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();
        result.append("{");

        for (Vector e : rows) {
            result.append(e + ", ");
        }

        result.append("}");
        return result.toString();
    }

    //TODO умножение матрицы на вектор


    public void add(Matrix matrix) {
        if (matrix.getRowsCount() != this.getRowsCount()) {
            throw new IllegalArgumentException("Размерности матриц не совпадают!");
        }

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            this.setRowVector(i, Vector.getAdd(this.getRowVector(i), matrix.getRowVector(i)));
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix.getRowsCount() != this.getRowsCount()) {
            throw new IllegalArgumentException("Размерности матриц не совпадают!");
        }

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            this.setRowVector(i, Vector.getSubtract(this.getRowVector(i), matrix.getRowVector(i)));
        }
    }
}

// TODO Статические методы:Сложение матриц

// TODO Статические методы Вычитание матриц

//TODO Статические методы Умножение матриц




