import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int MAX = 32;// Можно изменить ограничения на размер таблицы умножения
        Scanner scanner = new Scanner(System.in);
        int dimension;

        // Запрашиваем размер таблицы в цикле, пока не ввели правильное число
        do {
            System.out.print("Введите размер таблицы умножения,это целое число от 1 до " + MAX + " : ");
            try {
                dimension = scanner.nextInt();

                if (dimension > 0 && dimension < MAX) {
                    break;
                }
                if (dimension <= 0) {
                    System.out.println("Размер таблицы умножения должен быть больше 0!");
                }
                if (dimension > MAX) {
                    System.out.println("Размер таблицы умножения должен быть меньше " + MAX);
                }

            } catch (Exception e) {
                System.out.println("Вы ввели не число!");
                scanner.next();
            }

        } while (true);

        int firstCellLength = (int) (Math.log10(dimension) + 1);
        int cellLength = (int) (Math.log10(dimension * dimension) + 1);
        int rowLength = (cellLength + 1) * dimension + firstCellLength;

        StringBuilder firstRow = new StringBuilder(rowLength);
        firstRow.append(" ".repeat(firstCellLength));
        String cellFormat = "|%" + cellLength + "d";

        for (int i = 1; i <= dimension; i++) {
            firstRow.append(String.format(cellFormat, i));
        }

        StringBuilder cellsBorderRow = new StringBuilder(rowLength);
        String str = "-";
        String repeated = "+" + str.repeat(cellLength);
        cellsBorderRow.append("-".repeat(firstCellLength)).append(repeated.repeat(dimension));

        String firstCellFormat = "%" + firstCellLength + "d";

        System.out.println(firstRow);
        System.out.println(cellsBorderRow);

        for (int i = 1; i <= dimension; i++) {
            StringBuilder row = new StringBuilder(rowLength);
            row.append(String.format(firstCellFormat, i));

            for (int j = 1; j <= dimension; j++) {
                row.append(String.format(cellFormat, i * j));
            }

            System.out.println(row);
            System.out.println(cellsBorderRow);
        }
    }
}