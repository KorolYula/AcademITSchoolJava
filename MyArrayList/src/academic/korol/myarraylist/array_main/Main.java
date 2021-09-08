package academic.korol.myarraylist.array_main;

import academic.korol.myarraylist.array.MyArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

       MyArrayList<Number> list1 = new MyArrayList<>(4);
        list1.add(2);
        list1.add(5);
        list1.add(1);
        list1.add(9);
        System.out.println(Arrays.toString(list1.toArray()));


      MyArrayList<Integer> list2=new MyArrayList(5);
        list2.add(2);
        list2.add(8);
        System.out.println(Arrays.toString(list2.toArray()));

        boolean b= list1.addAll(list2);
        System.out.println(b);
        System.out.println(Arrays.toString(list1.toArray()));

    }
}
