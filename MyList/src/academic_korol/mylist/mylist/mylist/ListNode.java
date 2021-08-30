package academic_korol.mylist.mylist.mylist;

public class ListNode<T> {
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
        T old=this.data;
        this.data = data;
        return  old;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> t) {
        this.next=t;
    }
}

