package academic.korol.lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Петр", 24),
                new Person("Иван", 10),
                new Person("Ольга", 24),
                new Person("Ева", 32),
                new Person("Ольга", 40),
                new Person("Петр", 4)
        );

        List<String> allNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        String allNamesForPrint = allNames.stream()
                .collect(Collectors.joining(", "));
        System.out.println("Имена: " + allNamesForPrint + ".");

        persons.stream()
                .filter(p -> p.getAge() < 18)
                .mapToInt(Person::getAge)
                .average()
                .ifPresent(x -> System.out.println("Средний возраст людей, младше 18 лет: " + x));

        Map<String, Double> personsByName = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));

        personsByName.forEach((name, averageAge) ->
                System.out.printf(" %s: средний возраст %.2f%n", name, averageAge));

        String middleAgedPeopleNames = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.joining(", ", "Имена людей возраста от 20 до 45 лет: ", "."));
        System.out.println(middleAgedPeopleNames);
    }
}

