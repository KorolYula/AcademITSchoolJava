package academic.korol.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("inputCSV.txt"))) {
            try (PrintWriter out = new PrintWriter("output.html")) {
                out.println("<table  border=\"1\">");
                        out.println(" <caption>Таблица перевода CSV файла в  HTML</caption> ");
                // Почему двойной ободок?
                String fromFileString;
                String columnSeparator = ",";
                String quotes = "\"\"";
                String s2 = "\n";
                String[] list;
                while ((fromFileString = reader.readLine()) != null) {
                    list = fromFileString.split(columnSeparator);

                    out.println(" <tr>");
                    for (String s : list) {
                        out.println(" <td>"+s+"</td>");
                       // out.println(s);
                       // out.println(" </td>");
                    }
                    out.println(" </tr><br/>");
                }
            } catch (Exception e) {
                System.err.println("Ошибка при открытии файла " + e.getMessage());
            }
        }
    }
}



