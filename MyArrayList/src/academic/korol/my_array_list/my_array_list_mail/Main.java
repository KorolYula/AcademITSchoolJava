package academic.korol.my_array_list.my_array_list_mail;


import academic.korol.my_array_list.my_array_list.MyArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        MyArrayList<String> list1 = new MyArrayList<>(1);

      System.out.println(list1.contains(null));
        System.out.println(list1.size());
        list1.add("Olga");
        System.out.println(list1.size());

        list1.add("Petr");
        list1.add("Lena");
        list1.add("Max");
        System.out.println(Arrays.toString(list1.toArray()));
        list1.remove("Lena");
        list1.remove(null);

        System.out.println(Arrays.toString(list1.toArray()));

        /*System.out.println(list1.size());
        System.out.println(list1.size());




        MyArrayList<Integer> list2 = new MyArrayList(5);
        list2.add(2);
        list2.add(8);
        System.out.println(Arrays.toString(list2.toArray()));

        /*boolean b = list1.addAll(list2);
        System.out.println(b);
        System.out.println(Arrays.toString(list1.toArray()));
                 */
    }
    }

