package academic.korol.my_array_list_main;

import academic.korol.my_array_list.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> list1 = new MyArrayList<>();
        list1.add("Olga");
        System.out.println(list1.size());

        list1.add("Petr");
        list1.add("Lena");
        list1.add("Max");
        System.out.println(list1);

        list1.remove("Lena");
        System.out.println(list1.remove("Lena"));

        list1.set(2, null);
        System.out.println(list1);

        list1.add("Anna");
        list1.add("Eva");
        System.out.println(list1);

        System.out.println(list1.remove("Anna"));

        MyArrayList<Integer> list2 = new MyArrayList<>(5);
        list2.add(2);
        list2.add(8);
        System.out.println(list2);
    }
}