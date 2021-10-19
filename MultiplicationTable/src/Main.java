import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int MAX = 32;// Можно изменить ограничения на размер таблицы умножения
        Scanner scanner = new Scanner(System.in);
        int size;

        // Запрашиваем размер таблицы в цикле, пока не ввели правильное число
        do {
            System.out.print("Введите размер таблицы умножения,это целое число от 1 до " + MAX + " : ");
            try {
                size = scanner.nextInt();

                if (size > 0 && size <= MAX) {
                    break;
                }
                if (size <= 0) {
                    System.out.println("Размер таблицы умножения должен быть больше 0!");
                }
                if (size > MAX) {
                    System.out.println("Размер таблицы умножения должен быть меньше " + MAX);
                }

            } catch (Exception e) {
                System.out.println("Вы ввели не число!");
                scanner.next();
            }

        } while (true);

        int firstCellLength = (int) (Math.log10(size)) + 1;
        int cellLength = (int) (Math.log10(size * size)) + 1;
        int rowLength = (cellLength + 1) * size + firstCellLength;
        String cellFormat = "|%" + cellLength + "d";
        //Выводим первую строку
        System.out.print(" ".repeat(firstCellLength));

        for (int i = 1; i <= size; i++) {
            System.out.printf(cellFormat, i);
        }

        System.out.println();
        //Формируем строку-сетку таблицы
        StringBuilder cellsBorderRow = new StringBuilder(rowLength);
        String str = "-";
        String repeated = "+" + str.repeat(cellLength);
        cellsBorderRow.append("-".repeat(firstCellLength)).append(repeated.repeat(size));
        System.out.println(cellsBorderRow);

        String firstCellFormat = "%" + firstCellLength + "d";

        for (int i = 1; i <= size; i++) {
            System.out.printf(firstCellFormat, i);

            for (int j = 1; j <= size; j++) {
                System.out.printf(cellFormat, i * j);
            }

            System.out.println();
            System.out.println(cellsBorderRow);
        }
    }
}