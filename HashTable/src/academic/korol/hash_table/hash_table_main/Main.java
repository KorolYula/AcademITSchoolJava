package academic.korol.hash_table.hash_table_main;

import java.util.ArrayList;
import java.util.Arrays;

import academic.korol.hash_table.hash_table.MyHashTable;

public class Main {
    public static void main(String[] args) {
        MyHashTable<String> hashTableStrings = new MyHashTable<>();
        hashTableStrings.add("one");
        hashTableStrings.add("two");
        hashTableStrings.add("one");
        hashTableStrings.add("one");
        hashTableStrings.add("three");
        hashTableStrings.add("four");
        hashTableStrings.add(null);
        System.out.println(hashTableStrings);
        ArrayList<String> strings=new ArrayList<>(Arrays.asList("one",null));
        System.out.println(hashTableStrings.removeAll(strings));
        System.out.println(hashTableStrings);
    }
}