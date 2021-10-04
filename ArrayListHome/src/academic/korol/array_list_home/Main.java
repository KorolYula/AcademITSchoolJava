package academic.korol.array_list_home;

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
        public static ArrayList<String> readStringsListFromFile(String fileName) throws IOException {
        ArrayList<String> fileStrings = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String string;

            while ((string = reader.readLine()) != null) {
                fileStrings.add(string);
            }

        }
        return fileStrings;
    }

    public static void main(String[] args) {
        try {
            System.out.println(readStringsListFromFile("input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла " + e.getMessage());
        }

        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 5, 2, 4, 3, 3, 8, 12, 12, 13));
        System.out.println(numbers1);

        for (int i = 0; i < numbers1.size(); i++) {
            if (numbers1.get(i) % 2 == 0) {
                numbers1.remove(i);
                i--;
            }

        }

        System.out.println(numbers1);

        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 5, 2, 4, 3, 5, 2, 12, 12, 13));
        ArrayList<Integer> uniqueNumbers = new ArrayList<>(numbers2.size());

        for (Integer e : numbers2) {
            if (!uniqueNumbers.contains(e)) {
                uniqueNumbers.add(e);
            }

        }

        System.out.println(numbers2);
        System.out.println(uniqueNumbers);
    }
}