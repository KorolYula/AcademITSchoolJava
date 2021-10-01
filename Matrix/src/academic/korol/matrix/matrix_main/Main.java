package academic.korol.matrix.matrix_main;

import academic.korol.matrix.matrix.Matrix;
import academic.korol.vector.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(2, 3);
        System.out.println(matrix1);

        double[][] array = {{1, 2, 3}, {2}, {3, 4}, {4, 5, 6}};
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

        Vector v = matrix4.getRow(1);
        System.out.println(v);

        matrix1.setRow(1, v);
        System.out.println(matrix1);

        double[][] array1 = {{1, 2}, {2,2}, {3, 4}};
        Matrix matrix6 = new Matrix(array1);
        System.out.println(matrix6);

        double[][] array2 = {{1, 2,3}, {2,2,3}};
        Matrix matrix7 = new Matrix(array2);
        System.out.println(matrix7);

        System.out.println(Matrix.getDot(matrix6,matrix7));
        System.out.println(Matrix.getAdd(matrix1,matrix7));
        System.out.println(matrix1.getTransposed());

        double[][] array3 = {{1, 2,3,4}, {2,2,3,4},{4,2,4,1},{7,0,4,1}};
        Matrix matrix8 = new Matrix(array3);
        System.out.println(matrix8);
        System.out.println(Matrix.getSubtract(matrix2,matrix3));
        System.out.println(Matrix.getDeterminant(matrix8));
        System.out.println(matrix6);
        System.out.println(v);
        System.out.println(matrix6.getDotVector(v));
    }
}


