package ula.academits.java.range;

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

        if (userRange.isExactlyInside(point)) {
            System.out.println("Число принадлежит диапазону");
        } else {
            System.out.println("Число не принадлежит диапазону");
        }
        userRange.println();

        System.out.println("Введите начало второго диапазона: ");
        double from2 = scanner.nextDouble();

        System.out.println("Введите конец второго диапазона: ");
        double to2 = scanner.nextDouble();

        Range userRange2 = new Range(from2, to2);

        Range intersectionRange = userRange.getIntersection(userRange2);

        if (intersectionRange == null) {
            System.out.println("Пересечения нет.");
        } else {
            System.out.println("Интервал пересечения: ");
            intersectionRange.println();
        }

        Range[] unionRange = userRange.getUnion(userRange2);

        if (unionRange == null) {
            System.out.println("Объединения нет.");
        } else {
            System.out.println("Интервал объединения: ");
            userRange.println(unionRange);
        }

        Range[] subtractionRange = userRange.getSubtraction(userRange2);
        if (subtractionRange == null) {
            System.out.println("После вычитания интервал нулевой.");
        } else {
            System.out.println("Интервал разности: ");
            userRange.println(subtractionRange);
        }
    }
}