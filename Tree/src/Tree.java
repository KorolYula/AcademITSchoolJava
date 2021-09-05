import java.util.LinkedList;

public class Tree<T extends Comparable<T>> {

    private TreeNode<T> root;

    /*public void breadthFirstSearch() {
        LinkedList<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
        queue.addFirst(root);
        TreeNode<T> nodeCurrent = new TreeNode<>();

        for (TreeNode<T> t : queue) {
            nodeCurrent = t;
            // todoo
            queue.addFirst(t.getLeft());
            queue.addFirst(t.getRight());
            queue.remove(t);
        }
    }*/

    public TreeNode<T> getNode(T element) {
        TreeNode<T> nodeCurrent = root;
        while (nodeCurrent != null) {
            if (nodeCurrent.getData().compareTo(element)==0) {
                return nodeCurrent;
            }
            if (nodeCurrent.getData().compareTo(element) < 0) {
                nodeCurrent = nodeCurrent.getLeft();
            } else {
                nodeCurrent = nodeCurrent.getRight();
            }
        }
        return null;
    }


    public void addtNode(T element) {
        TreeNode<T> nodeCurrent = root;
        while (nodeCurrent != null) {
            if (nodeCurrent.getData().compareTo(element) < 0) {
                if (nodeCurrent.getLeft() != null) {
                    nodeCurrent = nodeCurrent.getLeft();
                } else {
                    TreeNode<T> newNode = new TreeNode<>(element);
                    nodeCurrent.setLeft(newNode);
                    return;
                }
            } else {
                if (nodeCurrent.getRight() != null) {
                    nodeCurrent = nodeCurrent.getRight();
                } else {
                    TreeNode<T> newNode = new TreeNode<>(element);
                    nodeCurrent.setRight(newNode);
                    return;
                }
            }
        }
    }

    public void removeNode(T element) {
        if (root.getData().compareTo(element)==0) {

        }

        TreeNode<T> nodeCurrent = root;
        if
        while (nodeCurrent != null) {
            if (nodeCurrent.equals(element)) {
                // DELETE 3 VARIANTA;
            }
            if (nodeCurrent.getData().compareTo(element) < 0) {
                nodeCurrent = nodeCurrent.getLeft();
            } else {
                nodeCurrent = nodeCurrent.getRight();
            }
        }

        }
    }


}




