package academic.korol.graph_main;

import academic.korol.graph.Graph;

public class Main {

    public static void main(String[] args) {
        int[][] graphTable = {
                {0, 0, 0, 1, 1, 0},
                {0, 0, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},};
        Graph graph = new Graph(graphTable);

        System.out.println("Обход графа в ширину: ");
        graph.breadthFirstSearch();

        System.out.println("Обход графа в глубину с рекурсией: ");
        graph.recursiveDepthFirstSearch();

        System.out.println("Обход графа в глубину: ");
        graph.depthFirstSearch();
    }
}