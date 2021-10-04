package academic.korol.graph_main;

import academic.korol.graph.Graph;

public class Main {

    public static void main(String[] args) {
        int[][] graphTable = {
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        Graph graph = new Graph(graphTable);

        boolean[] visited = new boolean[graphTable.length];
        graph.breadthFirstSearch();
        // graph.getSizeByDepthFirstRecursiveSearch();

    }
    }
