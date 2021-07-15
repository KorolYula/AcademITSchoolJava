package ula.academits.java.range.range_main;

import ula.academits.java.range.range.Range;

import java.util.Scanner;

public class Main {

    public static String toString(Range[] rangesArray) {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (Range e : rangesArray) {
            result.append(e.toString());
        }
        result.append("]");
        return result.toString().replace(")(", "),(");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начало диапазона: ");
        double from = scanner.nextDouble();

        System.out.println("Введите конец диапазона: ");
        double to = scanner.nextDouble();

        Range userRange1 = new Range(from, to);
        double rangeLength = userRange1.getLength();

        if (rangeLength < 0) {
            System.out.println("Ошибка при вводе диапазона.");
            return;
        }

        System.out.println("Введите вещественное число: ");
        double point = scanner.nextDouble();

        if (userRange1.isInside(point)) {
            System.out.print("Число принадлежит диапазону");
        } else {
            System.out.print("Число не принадлежит диапазону");
        }
        System.out.println(userRange1.toString());

        System.out.println("Введите начало второго диапазона: ");
        double from2 = scanner.nextDouble();

        System.out.println("Введите конец второго диапазона: ");
        double to2 = scanner.nextDouble();

        Range userRange2 = new Range(from2, to2);

        Range intersection = userRange1.getIntersection(userRange2);

        if (intersection == null) {
            System.out.println("Пересечения нет.");
        } else {
            System.out.print("Диапазон пересечения: ");
            System.out.println(intersection.toString());
        }

        Range[] union = userRange1.getUnion(userRange2);

        System.out.print("Диапазоны объединения: ");
        System.out.println(toString(union));

        Range[] difference = userRange1.getDifference(userRange2);

        System.out.print("Диапазоны разности: ");
        System.out.println(toString(difference));
    }
}