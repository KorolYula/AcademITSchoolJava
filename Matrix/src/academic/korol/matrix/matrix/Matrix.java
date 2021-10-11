package academic.korol.matrix.matrix;

import academic.korol.vector.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк в матрицы " + rowsCount +
                    " должно быть > 0");
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов в матрицы " + columnsCount +
                    " должно быть > 0");
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
        if (array.length == 0) {
            throw new IllegalArgumentException("Количество строк в матрицы должно быть > 0");
        }

        int maxSize = array[0].length;

        for (double[] e : array) {
            maxSize = Math.max(maxSize, e.length);
        }

        if (maxSize == 0) {
            throw new IllegalArgumentException("Количество столбцов в матрицы должно быть > 0");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxSize, array[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    private void checkIndex(int index, boolean isRow) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс " + index + " должен быть >= 0");
        }

        if (isRow) {
            if (index >= getRowsCount()) {
                throw new IllegalArgumentException("Индекс " + index +
                        " больше количества строк матрицы: " + getRowsCount());
            }
        } else if (index >= getColumnsCount()) {
            throw new IllegalArgumentException("Индекс " + index +
                    " больше количества столбцов матрицы: " + getColumnsCount());
        }
    }

    public Vector getRow(int rowIndex) {
        checkIndex(rowIndex, true);
        return new Vector(rows[rowIndex]);
    }

    public void setRow(int rowIndex, Vector vector) {
        checkIndex(rowIndex, true);
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Размерности матрицы " + getColumnsCount() +
                    " и вектора " + vector.getSize() + " не совпадают!");
        }

        for (int j = 0; j < vector.getSize(); j++) {
            rows[rowIndex].setComponent(j, vector.getComponent(j));
        }
    }

    public Vector getColumn(int columnIndex) {
        checkIndex(columnIndex, false);

        Vector column = new Vector(getRowsCount());

        for (int j = 0; j < getRowsCount(); j++) {
            column.setComponent(j, rows[j].getComponent(columnIndex));
        }

        return column;
    }

    public void multiply(double scalar) {
        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].multiplyByScalar(scalar);
        }
    }

    public void transpose() {
        Vector[] columns = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); i++) {
            columns[i] = getColumn(i);
        }

        rows = columns;
    }

    private static Matrix getMinor(Matrix matrix, int index) {
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

    public double getDeterminant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new UnsupportedOperationException("Для вычисления определителя матрицы количество столбцов " + getColumnsCount() +
                    " должно совпадать с количеством строк " + getRowsCount());
        }

        if (getRowsCount() == 1) {
            return getColumn(0).getComponent(0);
        }

        double determinant = 0;

        for (int i = 0; i < getRowsCount(); i++) {
            determinant += Math.pow(-1, i) * getColumn(0).getComponent(i) * getMinor(this, i).getDeterminant();
        }

        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (Vector e : rows) {
            stringBuilder.append(e);
            stringBuilder.append(",");
        }

        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    public Vector multiply(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Количество столбцов матрицы " + getColumnsCount() +
                    " и вектора " + vector.getSize() + " не совпадают!");
        }

        Vector result = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            result.setComponent(i, Vector.getDotProduct(vector, rows[i]));
        }

        return result;
    }

    private void checkDimension(Matrix matrix) {
        if (matrix.getRowsCount() != getRowsCount()) {
            throw new IllegalArgumentException("Количество строк второй матрицы  " + matrix.getRowsCount() +
                    " не совпадают с количеством строк первой матрицы " + getRowsCount());
        }

        if (matrix.getColumnsCount() != getColumnsCount()) {
            throw new IllegalArgumentException("Количество столбцов второй матрицы " + matrix.getColumnsCount() +
                    " не совпадают с количеством столбцов первой матрицы " + getColumnsCount());
        }
    }

    public void add(Matrix matrix) {
        checkDimension(matrix);

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkDimension(matrix);

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        matrix1.checkDimension(matrix2);
        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        matrix1.checkDimension(matrix2);
        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Операция невыполнима:" +
                    " количество столбцов матрицы1 = " + matrix1.getColumnsCount() +
                    " не совпадают" + " с количеством строк матрицы2 = " + matrix2.getRowsCount());
        }

        double[][] array = new double[matrix1.getRowsCount()][matrix2.getColumnsCount()];

        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                array[i][j] = Vector.getDotProduct(matrix1.rows[i], matrix2.getColumn(j));
            }
        }

        return new Matrix(array);
    }
}