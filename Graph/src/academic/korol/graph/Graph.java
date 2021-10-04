package academic.korol.graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    int[][] graph;

    public Graph(int[][] graph) {
        this.graph = graph;
    }

    public static boolean isVisited(boolean[] visited) {
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }

        return true;
    }


    public void depthFirstByRecursiveSearch(int vertexNumber, boolean[] visited) {
        System.out.println(vertexNumber + 1);
        visited[vertexNumber] = true;
        for (int k = 0; k < graph.length; k++) {
            if (graph[vertexNumber][k] == 1) {
                if (!visited[k]) {
                    depthFirstByRecursiveSearch(k, visited);
                }
            }
        }
    }

    public void recursiveDepthFirstSearch() {
        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                depthFirstByRecursiveSearch(i, visited);
            }
        }
    }

    public void breadthFirstSearch() {//обход в ширину
        int sizeGraph = graph.length;
        boolean[] visited = new boolean[sizeGraph];
        LinkedList<Integer> queue = new LinkedList<>();

        queue.addFirst(0);

        while (!isVisited(visited)) {
            Integer vertex = queue.remove();

            if (!visited[vertex]) {
                System.out.println(vertex + 1);
                visited[vertex] = true;

                for (int k = 0; k < sizeGraph; k++) {
                    if (graph[vertex][k] == 1) {
                        if (!visited[k]) {
                            queue.add(k);
                        }
                    }
                }
            }

            if (queue.isEmpty() && !isVisited(visited)) {
                int l = 0;

                while (l < sizeGraph) {
                    if (!visited[l]) {
                        break;
                    }
                    l++;
                }

                queue.add(l);
            }
        }
    }

    public void depthFirstSearch() {
        ArrayList<Integer> stack = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];

        stack.add(0);

        while (!stack.isEmpty()) {
            Integer vertex = stack.remove(stack.size() - 1);

            if (!visited[vertex]) {
                visited[vertex] = true;
                System.out.println(vertex + 1);
                visited[vertex] = true;

                for (int k = 0; k < graph.length; k++) {
                    if (graph[vertex][k] == 1) {
                        if (!visited[k]) {
                            stack.add(k);
                        }

                    }
                }
            }

            if (stack.isEmpty() && !isVisited(visited)) {
                int l = 0;

                while (l < graph.length) {
                    if (!visited[l]) {
                        break;
                    }
                    l++;
                }

                stack.add(l);
            }
        }
    }
}