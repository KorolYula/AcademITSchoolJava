package academic.korol.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Graph<V> {
    private final int[][] graph;
    private final ArrayList<V> vertices;

    public Graph(int[][] graph, ArrayList<V> vertices) {
        if (graph.length == 0) {
            throw new IllegalArgumentException("Количество вершин  в графе должно быть > 0");
        }

        if (graph.length != graph[0].length) {
            throw new IllegalArgumentException("Матрица для определения графа должна быть квадратной");
        }

        for (int i = 0; i < graph.length - 1; i++) {
            if (graph[i].length != graph[i + 1].length) {
                throw new IllegalArgumentException("Матрица для определения графа должна быть квадратной");
            }
        }

        this.graph = graph;
        this.vertices = vertices;
    }

    public static boolean notVisited(boolean[] visited) {
        for (boolean v : visited) {
            if (!v) {
                return true;
            }
        }

        return false;
    }

    private void depthFirstByRecursiveSearch(int vertexNumber, boolean[] visited, Consumer<V> consumer) {
        consumer.accept(vertices.get(vertexNumber));
        visited[vertexNumber] = true;

        for (int i = 0; i < graph.length; i++) {
            if (graph[vertexNumber][i] == 1 && !visited[i]) {
                depthFirstByRecursiveSearch(i, visited, consumer);
            }
        }
    }

    //обход в глубину с рекурсией
    public void recursiveDepthFirstSearch(Consumer<V> consumer) {
        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                depthFirstByRecursiveSearch(i, visited, consumer);
            }
        }
    }

    //обход в ширину
    public void breadthFirstSearch(Consumer<V> consumer) {
        int sizeGraph = graph.length;
        boolean[] visited = new boolean[sizeGraph];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (notVisited(visited)) {
            Integer vertex = queue.remove();

            if (!visited[vertex]) {
                consumer.accept(vertices.get(vertex));
                visited[vertex] = true;

                for (int i = 0; i < graph.length; i++) {
                    if (graph[vertex][i] == 1 && !visited[i]) {
                        queue.add(i);
                    }
                }
            }

            if (queue.isEmpty() && notVisited(visited)) {
                for (int i = 0; i < graph.length; i++) {
                    if (!visited[i]) {
                        queue.add(i);
                        break;
                    }
                }
            }
        }
    }

    //обход в глубину
    public void depthFirstSearch(Consumer<V> consumer) {
        ArrayList<Integer> stack = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        stack.add(0);

        while (!stack.isEmpty()) {
            Integer vertex = stack.remove(stack.size() - 1);

            if (!visited[vertex]) {
                visited[vertex] = true;
                consumer.accept(vertices.get(vertex));

                for (int i = graph.length - 1; i > 0; i--) {
                    if (graph[vertex][i] == 1 && !visited[i]) {
                        stack.add(i);
                    }
                }
            }

            if (stack.isEmpty() && notVisited(visited)) {
                for (int i = 0; i < graph.length; i++) {
                    if (!visited[i]) {
                        stack.add(i);
                        break;
                    }
                }
            }
        }
    }
}