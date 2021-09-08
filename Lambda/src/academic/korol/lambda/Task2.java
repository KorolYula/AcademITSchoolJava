package academic.korol.lambda;

import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число, сколько элементов корней чисел вы хотите вычислить: ");
        int count = scanner.nextInt();

        DoubleStream.iterate(0, x -> x + 1).map(Math::sqrt)
                .limit(count)
                .forEach(System.out::println);

      Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
              .limit(count)
              .map(n -> n[0])
              .forEach(System.out::println);

    }
}