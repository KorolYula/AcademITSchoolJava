package academic.korol.tree;

public class TreeNode<T extends Comparable<T>> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;

    public TreeNode() {
    }

    public TreeNode(T element) {
        data=element;
        left=null;
        right=null;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data=data;
    }




}



