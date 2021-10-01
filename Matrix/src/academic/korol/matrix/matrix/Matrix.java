package academic.korol.matrix.matrix;

import academic.korol.vector.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк в матрицы " + rowsCount + " должно быть > 0");
        }
        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцом в матрицы " + columnsCount + " должно быть > 0");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.getRowsCount()];

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("Количество строк в матрицы должно быть > 0");
        }

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
        if (array[0].length == 0) {
            throw new IllegalArgumentException("Количество строк в матрицы  должно быть > 0");
        }

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

    private void checkIndex(int index, boolean isRow) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс " + index + "должен быть >0 ");
        }
        if (isRow) {
            if (index >= getRowsCount()) {
                throw new ArrayIndexOutOfBoundsException("Индекс " + index + " больше размерности матрицы:" + getRowsCount());
            }
        } else if (index >= getColumnsCount()) {
            throw new ArrayIndexOutOfBoundsException("Индекс " + index + " больше размерности матрицы:" + getColumnsCount());
        }

    }

    public Vector getRow(int rowNumber) {
        checkIndex(rowNumber, true);
        return new Vector(rows[rowNumber]);
    }

    public void setRow(int rowNumber, Vector vector) {
        checkIndex(rowNumber, true);

        for (int j = 0; j < vector.getSize(); j++) {
            rows[rowNumber].setComponent(j, vector.getComponent(j));
        }
    }

    public Vector getColumn(int columnNumber) {
        checkIndex(columnNumber, false);

        Vector column = new Vector(getRowsCount());

        for (int j = 0; j < getRowsCount(); j++) {
            column.setComponent(j, getRow(j).getComponent(columnNumber));
        }

        return column;
    }

    public void multiply(double scalar) {
        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].multiplyByScalar(scalar);
        }
    }

    public Matrix getTransposed() {
        Matrix matrix = new Matrix(getColumnsCount(), getRowsCount());

        for (int i = 0; i < getColumnsCount(); i++) {
            matrix.setRow(i, getColumn(i));
        }

        return matrix;
    }

    public static Matrix getMinor(Matrix matrix, int index) {
        double[][] result = new double[matrix.getRowsCount() - 1][matrix.getRowsCount() - 1];

        int newIndex = 0;

        for (int i = 0; i < matrix.getRowsCount(); ) {
            if (i == index) {
                i++;
                continue;
            }

            for (int j = 1; j < matrix.getRowsCount(); j++) {
                result[newIndex][j - 1] = matrix.getRow(i).getComponent(j);
            }

            newIndex++;
            i++;
        }

        return new Matrix(result);

    }

    public static double getDeterminant(Matrix matrix) {
        if (matrix.getRowsCount() != matrix.getColumnsCount()) {
            throw new UnsupportedOperationException("Для вычисления определителя матрица должны быть квадратной!");
        }

        if (matrix.getRowsCount() == 1) {
            return matrix.getColumn(0).getComponent(0);
        }

        double determinant = 0;

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            determinant += Math.pow(-1, i ) * matrix.getColumn(0).getComponent(i) * getDeterminant(getMinor(matrix, i));
        }

        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");

        for (Vector e : rows) {
            result.append(e);
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
            result.setComponent(i, Vector.getDotProduct(vector, getColumn(i)));
        }

        return result;
    }

    private void dimensionCheck(Matrix matrix){
        if (matrix.getRowsCount() != this.getRowsCount()) {
            throw new IllegalArgumentException("Размерность строк матрицы "+matrix.getRowsCount()+" не совпадают с исходной "+this.getRowsCount());
        }

        if (matrix.getColumnsCount() != this.getColumnsCount()) {
            throw new IllegalArgumentException("Размерность строк матрицы "+matrix.getColumnsCount()+" не совпадают с исходной "+this.getColumnsCount());
        }
    }

    public void add(Matrix matrix) {
        dimensionCheck(matrix);

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            this.setRow(i, Vector.getSum(this.getRow(i), matrix.getRow(i)));
        }
    }

    public void subtract(Matrix matrix) {
        dimensionCheck(matrix);

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            this.setRow(i, Vector.getDifference(this.getRow(i), matrix.getRow(i)));
        }
    }

    public static Matrix getAdd(Matrix matrix1, Matrix matrix2) {
        matrix1.dimensionCheck(matrix2);
        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    public static Matrix getSubtract(Matrix matrix1, Matrix matrix2) {
        matrix1.dimensionCheck(matrix2);
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
                result[i][j] = Vector.getDotProduct(matrix1.getRow(i), matrix2.getColumn(j));
            }
        }

        return new Matrix(result);
    }
}
