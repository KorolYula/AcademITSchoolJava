package Academic_korol.array_list_home.array.array_main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> maArray1 = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {

            while (scanner.hasNextLine()) {
                maArray1.add(scanner.nextLine());
            }
        }
        System.out.println(maArray1);

        ArrayList<Integer> maArray2 = new ArrayList<>(Arrays.asList(1, 5, 2, 4, 3, 3, 8, 12, 12, 13));
        System.out.println(maArray2);

        for (int i = 0; i < maArray2.size(); i++) {
            if (maArray2.get(i) % 2 == 0) {
                maArray2.remove(i);
                i--;
            }
        }
        System.out.println(maArray2);

        ArrayList<Integer> maArray3 = new ArrayList<>(Arrays.asList(1, 5, 2, 4, 3, 5, 2, 12, 12, 13));
        ArrayList<Integer> newMaArray3 = new ArrayList<>();

        for (Integer e : maArray3) {
            if (!newMaArray3.contains(e)) {
                newMaArray3.add(e);
            }
        }
        System.out.println(maArray3);
        System.out.println(newMaArray3);
    }
}

