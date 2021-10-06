package academic.korol.my_list_main;

import academic.korol.my_list.MyList;

public class Main {
    public static void main(String[] args) {
        MyList<String> myList = new MyList<>("Olga");
        myList.add(1, "Petr");
        myList.add(2, "Igor");
        myList.add(3, "Anna");
        System.out.println(myList.toString());

        System.out.println(myList.getLength());

        myList.addFirst("Serge");
        myList.add(2, "Lena");
        System.out.println(myList.toString());

        System.out.println(myList.getFirst());
        System.out.println(myList.getData(2));

        System.out.println(myList.setData(3, "Max"));
        System.out.println(myList.toString());

        System.out.println(myList.delete("Lena"));
        System.out.println(myList.toString());

        myList.setData(2, null);
        System.out.println(myList.toString());

        System.out.println(myList.delete(null));
        System.out.println(myList.toString());

        myList.reverse();
        System.out.println(myList.toString());

        MyList<String> emptyList = new MyList<>();
        System.out.println(emptyList.toString());
    }
}