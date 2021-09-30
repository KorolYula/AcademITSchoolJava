package academic.korol.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private final Comparator<T> comparator;
    int size;

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public Tree() {
        this.comparator = (o1, o2) -> ((Comparable<T>) o1).compareTo(o2);
    }

    public int getSize() {
        return size;
    }

    private TreeNode<T> getNode(T element) {
        TreeNode<T> currentNode = root;
        while (currentNode != null) {
            int compare = comparator.compare(currentNode.getData(), element);
            if (compare == 0) {
                return currentNode;
            }

            if (compare < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }
        return null;
    }

    public boolean add(T element) {
        if (element == null) {
            return false;
        }

        if (root == null) {
            root = new TreeNode<>(element);
            size++;
            return true;
        }

        TreeNode<T> currentNode = root;

        while (currentNode != null) {
            try {
                if (comparator.compare(currentNode.getData(), element) > 0) {

                    if (currentNode.getLeft() != null) {
                        currentNode = currentNode.getLeft();
                    } else {
                        TreeNode<T> newNode = new TreeNode<>(element);
                        size++;
                        currentNode.setLeft(newNode);
                        return true;
                    }

                } else {
                    if (currentNode.getRight() != null) {
                        currentNode = currentNode.getRight();
                    } else {
                        TreeNode<T> newNode = new TreeNode<>(element);
                        size++;
                        currentNode.setRight(newNode);
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException e) {
                return false;

            }
        }
        return false;
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
                currentNode = leftList;
            }
        }
        size--;
    }

    public void removeRoot() {
        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
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

            root = leftList;
        } else {
            while (leftList.getLeft() != null) {
                leftListParent = leftList;
                leftList = leftList.getLeft();
            }

            leftListParent.setLeft(leftList.getRight());
            leftList.setLeft(root.getLeft());
            leftList.setRight(root.getRight());
            root = leftList;

        }
        size--;
    }

    public boolean remove(T element) {
        if (comparator.compare(root.getData(),element) == 0) {
            removeRoot();
            return true;
        }

        TreeNode<T> currentNode = root;
        TreeNode<T> parentNode = null;

        while (currentNode != null) {
            if (comparator.compare(currentNode.getData(),element) == 0) {
                removeCurrentNode(parentNode, currentNode);
                return true;
            }

            if (comparator.compare(currentNode.getData(),element) < 0) {
                parentNode = currentNode;
                currentNode = currentNode.getLeft();
            } else {
                parentNode = currentNode;
                currentNode = currentNode.getRight();
            }
        }
        return false;
    }

    //обход дерева в глубину с рекурсией
    public void depthFirstRecursiveSearch(TreeNode<T> node,Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());
        depthFirstRecursiveSearch(node.getLeft(),consumer);
        depthFirstRecursiveSearch(node.getRight(),consumer);
    }

    public void getSizeByDepthFirstRecursiveSearch(Consumer<T> consumer) {
        depthFirstRecursiveSearch(root,consumer);
    }

    //обход дерева в ширину
    public void breadthFirstSearch(Consumer<T> consumer) {
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
        ArrayList<TreeNode<T>> stack = new ArrayList<>();
        if (root == null) {
            return;
        }
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