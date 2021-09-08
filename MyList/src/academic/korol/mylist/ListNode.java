package academic.korol.mylist;

 class ListNode<T> {
    private T data;
    private ListNode<T> next;

    public ListNode(T data) {
        this.data = data;
    }

    public ListNode(T data, ListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public T setData(T data) {
        T oldData = this.data;
        this.data = data;
        return oldData;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> nextNode) {
        this.next = nextNode;
    }
}

