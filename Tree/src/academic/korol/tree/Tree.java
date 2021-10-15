package academic.korol.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private final Comparator<T> comparator;
    private int size;

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public Tree() {
        this.comparator = (o1, o2) -> {
            if (o1 == null) {
                if (o2 == null) {
                    return 0;
                }

                return -1;
            }

            if (o2 == null) {
                return 1;
            }

            //noinspection unchecked
            return ((Comparable<T>) o1).compareTo(o2);
        };
    }

    public int getSize() {
        return size;
    }

    private TreeNode<T> getNode(T element) {
        TreeNode<T> currentNode = root;

        while (currentNode != null) {
            int compareResult = comparator.compare(currentNode.getData(), element);

            if (compareResult == 0) {
                return currentNode;
            }

            if (compareResult < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        return null;
    }

    public void add(T element) {
        if (root == null) {
            root = new TreeNode<>(element);
            size++;
            return;
        }

        TreeNode<T> currentNode = root;

        while (currentNode != null) {
            if (comparator.compare(currentNode.getData(), element) > 0) {

                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    TreeNode<T> newNode = new TreeNode<>(element);
                    size++;
                    currentNode.setLeft(newNode);
                    return;
                }

            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    TreeNode<T> newNode = new TreeNode<>(element);
                    size++;
                    currentNode.setRight(newNode);
                    return;
                }
            }
        }
    }


    private void removeCurrentNode(TreeNode<T> parentNode, TreeNode<T> currentNode) {
        if (currentNode.getRight() == null && currentNode.getLeft() == null) { //если лист
            if (parentNode.getRight() == currentNode) {
                parentNode.setRight(null);
            } else {
                parentNode.setLeft(null);
            }
        }

        if (currentNode.getLeft() == null && currentNode.getRight() != null) {//если нет левого ребенка
            if (parentNode.getRight() == currentNode) {
                parentNode.setRight(currentNode.getRight());
            } else {
                parentNode.setLeft(currentNode.getRight());
            }
        }

        if (currentNode.getRight() == null && currentNode.getLeft() != null) {//если нет правого ребенка
            if (parentNode.getLeft() == currentNode) {
                parentNode.setLeft(currentNode.getLeft());
            } else {
                parentNode.setRight((currentNode.getLeft()));
            }
        } else {// У него есть 2 ребенка, ищем самый левый
            TreeNode<T> leftList = currentNode.getRight();
            TreeNode<T> leftListParent = currentNode;

            if (leftList.getLeft() == null) {
                leftList.setLeft(currentNode.getLeft());
                parentNode.setRight(leftList);
            } else {
                while (leftList.getLeft() != null) {
                    leftListParent = leftList;
                    leftList = leftList.getLeft();
                }

                leftListParent.setLeft(leftList.getRight());
                leftList.setLeft(currentNode.getLeft());
                leftList.setRight(currentNode.getRight());
                parentNode.setLeft(leftList);
            }
        }

        size--;
    }

    private void removeRoot() {
        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
            size--;
            return;
        }

        if (root.getLeft() == null) {
            root = root.getRight();
        }

        if (root.getRight() == null) {
            root = root.getLeft();
        }

        TreeNode<T> leftList = root.getRight();
        TreeNode<T> leftListParent = root;

        if (leftList.getLeft() == null) {
            leftList.setLeft(root.getLeft());

        } else {
            while (leftList.getLeft() != null) {
                leftListParent = leftList;
                leftList = leftList.getLeft();
            }

            leftListParent.setLeft(leftList.getRight());
            leftList.setLeft(root.getLeft());
            leftList.setRight(root.getRight());
        }
        root = leftList;

        size--;
    }

    public boolean remove(T element) {
        if (root == null) {
            return false;
        }

        if (comparator.compare(root.getData(), element) == 0) {
            removeRoot();
            return true;
        }

        TreeNode<T> currentNode = root;
        TreeNode<T> parentNode = null;

        while (currentNode != null) {
            int compareResult = comparator.compare(currentNode.getData(), element);

            if (compareResult == 0) {
                removeCurrentNode(parentNode, currentNode);
                return true;
            }

            parentNode = currentNode;
            if (compareResult > 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        return false;
    }

    //обход дерева в глубину с рекурсией
    private void recursiveDepthFirstSearch(TreeNode<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());
        recursiveDepthFirstSearch(node.getLeft(), consumer);
        recursiveDepthFirstSearch(node.getRight(), consumer);
    }

    public void depthFirstRecursiveSearch(Consumer<T> consumer) {
        recursiveDepthFirstSearch(root, consumer);
    }

    //обход дерева в ширину
    public void breadthFirstSearch(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode<T>> queue = new LinkedList<>();
        queue.addFirst(root);

        while (!queue.isEmpty()) {
            TreeNode<T> nodeCurrent = queue.remove();
            consumer.accept(nodeCurrent.getData());

            if (nodeCurrent.getLeft() != null) {
                queue.add(nodeCurrent.getLeft());
            }

            if (nodeCurrent.getRight() != null) {
                queue.add(nodeCurrent.getRight());
            }
        }
    }

    //обход в глубину без рекурсии
    public void depthFirstSearch(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        ArrayList<TreeNode<T>> stack = new ArrayList<>(size);
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<T> nodeCurrent = stack.remove(stack.size() - 1);
            consumer.accept(nodeCurrent.getData());

            if (nodeCurrent.getRight() != null) {
                stack.add(nodeCurrent.getRight());
            }

            if (nodeCurrent.getLeft() != null) {
                stack.add(nodeCurrent.getLeft());
            }
        }
    }
}