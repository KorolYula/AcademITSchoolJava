package academic.korol.tree_main;

import academic.korol.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(18);
        tree.add(10);
        tree.add(4);
        tree.add(14);
        tree.add(12);
        tree.add(16);
        tree.add(13);
        System.out.println("Размер дерева" + tree.getSize());
        System.out.println();
        tree.depthFirstSearch(System.out::println);
        tree.remove(10);

        tree.depthFirstSearch(System.out::println);

        System.out.println("Размер дерева" + tree.getSize());
        System.out.println("Обход в глубину без рекурсии");
        tree.depthFirstSearch(System.out::println);

        System.out.println("Обход в ширину");
        tree.breadthFirstSearch(System.out::println);
        System.out.println("Обход в глубину с рекурсией");

        tree.depthFirstRecursiveSearch(System.out::println);
    }
}