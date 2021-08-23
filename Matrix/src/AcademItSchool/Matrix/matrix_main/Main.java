package AcademItSchool.Matrix.matrix_main;

import AcademItSchool.Matrix.matrix.Matrix;
import Academic_korol.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(1, 3);


        Vector vector1 = new Vector(new double[]{1, 2, 3});
        Vector vector2 = new Vector(new double[]{2, 4, 6});
        Matrix matrix2 = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 3}), new Vector(new double[]{2, 4, 6})});

        Matrix matrix3 = new Matrix(matrix2);

        double[][] array = {{1, 2, 3}, {2, 3, 4}, {3, 4, 5}, {4, 5, 6}};
        Matrix matrix4 = new Matrix(array);
        System.out.println(matrix4);
    }
}


