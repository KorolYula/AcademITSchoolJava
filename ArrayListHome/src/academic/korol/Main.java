package academic.korol;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> readFile() throws FileNotFoundException {
        ArrayList<String> stringsArray = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNextLine()) {
                stringsArray.add(scanner.nextLine());
            }
        }

        return stringsArray;
    }

    public static void main(String[] args) {
        try {
            System.out.println(readFile());
        } catch (FileNotFoundException e) {
            System.out.println("Фаил не найден");
        } finally {
            ArrayList<Integer> oddNumbersArray = new ArrayList<>(Arrays.asList(1, 5, 2, 4, 3, 3, 8, 12, 12, 13));
            System.out.println(oddNumbersArray);

            for (int i = 0; i < oddNumbersArray.size(); i++) {
                if (oddNumbersArray.get(i) % 2 == 0) {
                    oddNumbersArray.remove(i);
                    i--;
                }
            }

            System.out.println(oddNumbersArray);

            ArrayList<Integer> numbersArray = new ArrayList<>(Arrays.asList(1, 5, 2, 4, 3, 5, 2, 12, 12, 13));
            ArrayList<Integer> uniqueNumbersArray = new ArrayList<>(numbersArray.size());

            for (Integer e : numbersArray) {
                if (!uniqueNumbersArray.contains(e)) {
                    uniqueNumbersArray.add(e);
                }
            }

            System.out.println(numbersArray);
            System.out.println(uniqueNumbersArray);
        }
    }
}




