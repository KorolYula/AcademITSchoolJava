package academic.korol.mylist_main;

import academic.korol.mylist.MyList;

public class Main {
    public static void main(String[] args) {
        MyList<String> myList = new MyList<>("Olga");
        myList.addNode(1, "Petr");
        myList.addNode(2, "Igor");
        myList.addNode(3, "Anna");
        System.out.println(myList);

        System.out.println(myList.getLength());

        myList.addFirst("Serge");
        myList.addNode(2, "Lena");
        System.out.println(myList);

        System.out.println(myList.getFirst());
        System.out.println(myList.getData(2));

        System.out.println(myList.setData(3, "Max"));
        System.out.println(myList);

        System.out.println(myList.deleteNode("Lena"));
        System.out.println(myList);
        myList.revers();
        System.out.println(myList);
    }
}
