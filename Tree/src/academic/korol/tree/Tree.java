package academic.korol.tree;

import java.util.ArrayList;
import java.util.LinkedList;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;

    public TreeNode<T> getNode(T element) {
        TreeNode<T> currentNode = root;
        while (currentNode != null) {
            if (currentNode.getData().compareTo(element) == 0) {
                return currentNode;
            }

            if (currentNode.getData().compareTo(element) < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }
        return null;
    }

    public void addNode(T element) {
        if (root == null) {
            root = new TreeNode<>(element);
            return;
        }

        TreeNode<T> currentNode = root;

        while (currentNode != null) {
            if (currentNode.getData().compareTo(element) < 0) {

                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    TreeNode<T> newNode = new TreeNode<>(element);
                    currentNode.setLeft(newNode);
                    return;
                }

            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    TreeNode<T> newNode = new TreeNode<>(element);
                    currentNode.setRight(newNode);
                    return;
                }
            }
        }
    }

    public void removeCurrentNode(TreeNode<T> parentNode, TreeNode<T> currentNode) {
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
        } else {//у него есть 2 ребенка.. ищем самый левый
            TreeNode<T> leftList = currentNode.getRight();
            TreeNode<T> leftListParent = currentNode;
            if (leftList.getLeft() == null) {
                currentNode.setData(leftList.getData());
                parentNode.setRight(leftList);
            } else {
                while (leftList.getLeft() != null) {
                    leftListParent = leftList;
                    leftList = leftList.getLeft();
                }
                leftListParent.setLeft(leftList.getRight());
                currentNode.setData(leftList.getData());
            }
        }
    }

    public void removeRoot() {
        if (root.getLeft() == null) {
            root = root.getRight();
        }

        if (root.getRight() == null) {
            root = root.getLeft();
        }

        TreeNode<T> leftList = root.getRight();
        TreeNode<T> leftListParent = root;

        while (leftList.getLeft() != null) {
            leftListParent = leftList;
            leftList = leftList.getLeft();
        }

        leftListParent.setLeft(leftList.getRight());
        root.setData(leftList.getData());
    }

    public boolean removeNode(T element) {
        if (root.getData().compareTo(element) == 0) {
            removeRoot();
            return true;
        }

        TreeNode<T> currentNode = root;
        TreeNode<T> parentNode = null;

        while (currentNode != null) {
            if (currentNode.getData().compareTo(element) == 0) {
                removeCurrentNode(parentNode, currentNode);
                return true;
            }

            if (currentNode.getData().compareTo(element) < 0) {
                parentNode = currentNode;
                currentNode = currentNode.getLeft();
            } else {
                parentNode = currentNode;
                currentNode = currentNode.getRight();
            }
        }
        return false;
    }

    //обход дерева в глуину с рекурсией
    public int depthFirstRecursiveSearch(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }

        return 1 + depthFirstRecursiveSearch(node.getLeft()) + depthFirstRecursiveSearch(node.getRight());
    }

    public int getSizeByDepthFirstRecursiveSearch(Tree<T> tree) {
        return depthFirstRecursiveSearch(root);
    }

    //обход дерева в ширину
    public int breadthFirstSearch() {
        LinkedList<TreeNode<T>> queue = new LinkedList<>();
        int treeSize = 0;
        queue.addFirst(root);

        while (!queue.isEmpty()) {
            TreeNode<T> nodeCurrent = queue.remove();

            if (nodeCurrent.getLeft() != null) {
                queue.add(nodeCurrent.getLeft());
            }

            if (nodeCurrent.getRight() != null) {
                queue.add(nodeCurrent.getRight());
            }

            treeSize++;
        }
        return treeSize;
    }

    //обход в глубину без рекурсии
    public int depthFirstSearch() {
        int treeSize = 0;
        ArrayList<TreeNode<T>> stack = new ArrayList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<T> nodeCurrent = stack.remove(stack.size() - 1);

            if (nodeCurrent.getLeft() != null) {
                stack.add(nodeCurrent.getLeft());
            }

            if (nodeCurrent.getRight() != null) {
                stack.add(nodeCurrent.getRight());
            }

            treeSize++;
        }

        return treeSize;
    }
}