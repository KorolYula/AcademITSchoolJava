package academic.korol.hash_table.hash_table_main;

import java.util.ArrayList;
import java.util.Arrays;

import academic.korol.hash_table.hash_table.MyHashTable;

public class Main {
    public static void main(String[] args) {
        MyHashTable<String> stringsHashTable = new MyHashTable<>();
        stringsHashTable.add("one");
        stringsHashTable.add("two");
        stringsHashTable.add("one");
        stringsHashTable.add("one");
        stringsHashTable.add("three");
        stringsHashTable.add("four");
        stringsHashTable.add(null);

        System.out.println(stringsHashTable);

        ArrayList<String> strings = new ArrayList<>(Arrays.asList("one", null));

        System.out.println(stringsHashTable.removeAll(strings));
        System.out.println(stringsHashTable);
    }
}