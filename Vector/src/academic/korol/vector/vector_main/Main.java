package academic.korol.vector.vector_main;

import academic.korol.vector.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(3);
        System.out.println(vector1);

        double[] array = {1, 3, 4, 10};
        Vector vector2 = new Vector(array);
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

        vector5.multiplyByScalar(2);
        System.out.println(vector5);

        vector5.reverse();
        System.out.println(vector5);

        System.out.println(vector5.getLength());

        System.out.println(vector5.equals(vector3));

        Vector vector6 = new Vector(new double[]{-2, -6, -12, -20});
        System.out.println(vector5.equals(vector6));

        Vector vector7 = Vector.getSum(vector4, vector5);
        System.out.println(vector7);

        Vector vector8 = Vector.getDifference(vector4, vector2);
        System.out.println(vector8);

        System.out.println(vector8.hashCode());

        System.out.println(Vector.getDotProduct(vector8, vector7));

        Vector vector9 = new Vector(9, vector2);
        System.out.println(vector9);
    }
}

