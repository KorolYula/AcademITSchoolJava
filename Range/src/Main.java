import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начало диапазона: ");
        double from = scanner.nextDouble();

        System.out.println("Введите конец диапазона: ");
        double to = scanner.nextDouble();

        Range userRange = new Range(from, to);
        double rangeLength = userRange.getLength();

        if (rangeLength < 0) {
            System.out.println("Ошибка при вводе диапазона.");
            return;
        }

        System.out.println("Введите вещественное число: ");
        double point = scanner.nextDouble();

        if (userRange.isInside(point)) {
            System.out.println("Число принадлежит диапазону");
            userRange.printLn();
        } else {
            System.out.println("Число не принадлежит диапазону");
            userRange.printLn();
        }
    }
}
