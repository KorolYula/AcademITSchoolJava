package academic.korol.graph_main;

import academic.korol.graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[][] graphTable = {
                {0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        ArrayList<String> graphArray = new ArrayList<>(Arrays.asList("Машина", "Самокат", "Велик", "Морковка", "Банан", "Канат", "Ботинок"));
        Graph<String> graph = new Graph<>(graphTable, graphArray);

        System.out.println("Обход графа в ширину: ");
        graph.breadthFirstSearch(System.out::println);

        System.out.println("Обход графа в глубину с рекурсией: ");
        graph.recursiveDepthFirstSearch(System.out::println);

        System.out.println("Обход графа в глубину: ");
        graph.depthFirstSearch(System.out::println);
    }
}