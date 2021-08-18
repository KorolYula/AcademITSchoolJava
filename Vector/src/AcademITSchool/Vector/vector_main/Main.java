package AcademITSchool.Vector.vector_main;

import AcademITSchool.Vector.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(2);
        System.out.println(vector1);

        double[] vv = {1, 3, 4, 10};
        Vector vector2 = new Vector(vv);
        System.out.println(vector2);

        System.out.println(vector2.getSize());

        System.out.println(vector2.getComponent(1));

        vector2.setComponent(2, 6);
        System.out.println(vector2);

        Vector vector3 = new Vector(6, new double[]{7, 8, 9});
        System.out.println(vector3);

        Vector vector4 = new Vector(new double[]{7, 8, 9});
        System.out.println(vector4);

        Vector vector5 = new Vector(vector2);
        System.out.println(vector5);

        vector4.add(vector5);
        System.out.println(vector4);

        vector3.subtract(vector5);
        System.out.println(vector3);

        vector5.scale(2);
        System.out.println(vector5);

        vector5.negate();
        System.out.println(vector5);

        System.out.println(vector5.getVectorLength());

        System.out.println(vector5.equals(vector3));

        Vector vector6 = new Vector(new double[]{-2, -6, -12, -20});
        System.out.println(vector5.equals(vector6));

        Vector vector7 = Vector.add(vector4, vector5);
        System.out.println(vector7);

        Vector vector8 = Vector.subtract(vector4, vector2);
        System.out.println(vector8);
    }
}
