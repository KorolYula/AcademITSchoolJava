package academic.korol.matrix.matrix_main;

import academic.korol.matrix.matrix.Matrix;
import academic.korol.vector.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(2, 3);
        System.out.println(matrix1);

        double[][] array = {{}, {1, 5}};
        Matrix matrix2 = new Matrix(array);
        System.out.println(matrix2);

        Matrix matrix3 = new Matrix(matrix2);
        System.out.println(matrix3);

        Vector vector1 = new Vector(new double[]{1, 2});
        Vector vector2 = new Vector(new double[]{2, 4, 6});
        Matrix matrix4 = new Matrix(new Vector[]{vector1, vector2});
        System.out.println(matrix4);

        matrix3.add(matrix2);
        System.out.println(matrix3);

        matrix4.multiply(3.0);
        System.out.println(matrix4);

        Vector vector = matrix4.getRow(1);
        System.out.println(vector);

        matrix1.setRow(1, vector);
        System.out.println(matrix1);

        double[][] array1 = {{1, 2}, {2, 2}, {3, 4}};
        Matrix matrix6 = new Matrix(array1);
        System.out.println(matrix6);

        double[][] array2 = {{1, 2, 3}, {2, 2, 3}};
        Matrix matrix7 = new Matrix(array2);
        System.out.println(matrix7);

        System.out.println(matrix7.getColumnsCount());

        System.out.println(matrix6.getColumnsCount());

        System.out.println(Matrix.getDotProduct(matrix6, matrix7));

        System.out.println(Matrix.getSum(matrix1, matrix7));

        System.out.println(matrix1);
        matrix1.transpose();
        System.out.println(matrix1);
    }
}