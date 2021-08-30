package AcademItSchool.Matrix.matrix;

import academic_korol.vector.Vector;


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
        rows = new Vector[matrix.getRowsCount()];

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        int maxSize = vectorsArray[0].getSize();

        for (Vector e : vectorsArray) {
            maxSize = Math.max(maxSize, e.getSize());
        }

        rows = new Vector[vectorsArray.length];

        for (int i = 0; i < vectorsArray.length; i++) {
            rows[i] = new Vector(maxSize, vectorsArray[i]);
        }
    }

    public Matrix(double[][] array) {
        int maxSize = array[0].length;

        for (double[] e : array) {
            maxSize = Math.max(maxSize, e.length);
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxSize, array[i]);
        }
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
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Размерность вектора не совпадает с размерностью матрицы");
        }
        for (int j = 0; j < vector.getSize(); j++) {
            rows[i].setComponent(j, vector.getComponent(j));
        }
    }

    public Vector getColumnVector(int i) {
        if (i > getColumnsCount()) {
            throw new IllegalArgumentException("Выход за пределы матрицы");
        }

        Vector v = new Vector(getRowsCount());

        for (int j = 0; j < getRowsCount(); j++) {
            v.setComponent(j, getRowVector(j).getComponent(i));
        }

        return v;
    }

    public void scale(double scalar) {
        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].multiplyByScaler(scalar);
        }
    }

    public Matrix getTransposed() {
        Matrix matrix = new Matrix(this.getColumnsCount(), getRowsCount());

        for (int i = 0; i < getColumnsCount(); i++) {
            matrix.setRowVector(i, getColumnVector(i));
        }

        return matrix;
    }

    public static  Matrix getMinor(Matrix matrix, int index) {
        double[][] result = new double[matrix.getRowsCount() - 1][matrix.getRowsCount() - 1];

       int  indexNew=0;

        for (int indexOld = 0; indexOld < matrix.getRowsCount();) {
            if (indexOld == index) {
                indexOld++;
                continue;
            }

            for (int j = 1; j < matrix.getRowsCount(); j++) {
                result[indexNew][j - 1] = matrix.getRowVector(indexOld).getComponent(j);
            }

                indexNew++;
                indexOld++;
        }

        return new Matrix(result);

}

    public static double getDeterminant(Matrix matrix) {
        if (matrix.getRowsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Для вычисления определителя матрица должны быть квадратной!");
        }

        if (matrix.getRowsCount() == 1) {
            return matrix.getColumnVector(0).getComponent(0);
        }

        double determinant = 0;

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            determinant += Math.pow(-1, i+2) * matrix.getColumnVector(0).getComponent(i) * getDeterminant(getMinor(matrix, i));
        }

        return  determinant;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");

        for (Vector e : rows) {
            result.append(e.toString());
            result.append(", ");
        }

        result.delete(result.length() - 2, result.length() - 1);
        result.append("}");

        return result.toString();
    }

    public Vector getDotVector(Vector vector) {
        if (vector.getSize() != getRowsCount()) {
            throw new IllegalArgumentException("Размерности матрицы и вектора не совпадают!");
        }

        Vector result = new Vector(getColumnsCount());

        for (int i = 0; i < getColumnsCount(); i++) {
            result.setComponent(i, Vector.getDotProduct(vector, getColumnVector(i)));
        }

        return result;
    }

    public void add(Matrix matrix) {
        if (matrix.getRowsCount() != this.getRowsCount()) {
            throw new IllegalArgumentException("Размерности матриц не совпадают!");
        }

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            this.setRowVector(i, Vector.getSum(this.getRowVector(i), matrix.getRowVector(i)));
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix.getRowsCount() != this.getRowsCount()) {
            throw new IllegalArgumentException("Размерности матриц не совпадают!");
        }

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            this.setRowVector(i, Vector.getDifference(this.getRowVector(i), matrix.getRowVector(i)));
        }
    }

    public static Matrix getAdd(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Размерности матриц не совпадают!");
        }

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    public static Matrix getSubtract(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Размерности матриц не совпадают!");
        }

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    public static Matrix getDot(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Размерности матриц не совпадают!");
        }

        double[][] result = new double[matrix1.getRowsCount()][matrix2.getColumnsCount()];

        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                result[i][j] = Vector.getDotProduct(matrix1.getRowVector(i), matrix2.getColumnVector(j));
            }
        }

        return new Matrix(result);
    }
}
