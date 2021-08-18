package AcademITSchool.Vector.vector_main;

import AcademITSchool.Vector.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(1);
        double[] vv = {1, 3, 4, 10};
        Vector vector2 = new Vector(vv);
        Vector vector4 = new Vector(6, vv);
        Vector vector3=new Vector(new double[]{7, 8, 9});
        System.out.println(vector1);
        System.out.println(vector2);
        System.out.println(vector3);

        System.out.println(vector2.getSize());
        System.out.println(vector2.getComponent(1));


    }
}
