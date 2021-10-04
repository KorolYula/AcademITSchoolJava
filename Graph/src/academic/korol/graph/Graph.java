package academic.korol.graph;

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

   /* public void depthFirstRecursiveSearch(int i) {
        System.out.println(i + 1);
        for (int k = 0; k < graph.length; k++) {
            if (!visited[k]) {
                depthFirstRecursiveSearch(k);
            }
        }
    }


    public void depthFirstRecursiveSearch() {

        boolean[] visited = new boolean[graph.length];


        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                depthFirstRecursiveSearch(i);
                visited[i]=true;
            }
        }
    }
*/
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


}
