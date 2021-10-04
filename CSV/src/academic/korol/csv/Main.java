package academic.korol.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("inputCSV.txt"))) {
            try (PrintWriter out = new PrintWriter("outputCSV.txt")) {
                String string;
                String columnSeparator = ",";
                String quotes = """;

                String[] list;
                while ((string = reader.readLine()) != null) {

                    list = string.split(columnSeparator);

                }

            }

            BufferedReader[] in = new BufferedReader["inputCSV.txt"];
            // заполняем первыми строками из всех файлов, смотрим, если какие-то строки пустые, то файлы не берем в расчет
            for (int i = 0; i < fileAmount; i++) {
                try {
                    in[i] = new BufferedReader(new FileReader(fileNames[i]));
                    stringData[i] = readFile(in, i, isString, fileNames);
                    if (stringData[i] == null) {
                        continue;
                    }
                    fileLeft++;
                } catch (IOException ex1) {
                    System.err.println("Не найден файл " + fileNames[i]);
                    stringData[i] = null;
                }
            }

            String prev = null;

            try (PrintWriter out = new PrintWriter(outFileName)) {

                while (fileLeft > 0) {
                    try {
                        int index = getIndex(stringData, isSortAscending, isString, prev);
                        out.println(stringData[index]);
                        prev = stringData[index];
                        stringData[index] = readFile(in, index, isString, fileNames);
                        if (stringData[index] == null) fileLeft--;
                    } catch (WrongInputException error) {
                        System.err.println("Неотсортированное входное значение \"" + stringData[error.index] + "\" в фаиле " + fileNames[error.index]);
                        stringData[error.index] = readFile(in, error.index, isString, fileNames);
                        if (stringData[error.index] == null) fileLeft--;
                    }
                }
            } catch (Exception e) {
                System.err.println("Ошибка при открытии файла " + e.getMessage());
                System.exit(-1);
            }

        }
    }


}
