package academic.korol.my_array_list.my_array_list_main;

import academic.korol.my_array_list.my_array_list.MyArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> list1 = new MyArrayList<>();
        list1.add("Olga");
        System.out.println(list1.size());

        list1.add("Petr");
        list1.add("Lena");
        list1.add("Max");
        System.out.println(Arrays.toString(list1.toArray()));

        list1.remove("Lena");
        System.out.println(list1.remove("Lena"));

        list1.set(2, null);
        System.out.println(Arrays.toString(list1.toArray()));

        list1.add("Anna");
        list1.add("Eva");
        System.out.println(Arrays.toString(list1.toArray()));

        System.out.println(list1.remove("Anna"));

        MyArrayList<Integer> list2 = new MyArrayList<>(5);
        list2.add(2);
        list2.add(8);
        System.out.println(Arrays.toString(list2.toArray()));
    }
}