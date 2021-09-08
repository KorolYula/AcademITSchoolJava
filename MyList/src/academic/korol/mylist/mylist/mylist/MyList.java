package academic.korol.mylist.mylist.mylist;

public class MyList<T> {
    private ListNode<T> head;
    int length;

    public MyList() {
    }


    public int getLength() {
        return length;
    }

    public T getPremierElement() {
        return head.getData();
    }

    public void addPremierElement(T data) {
        head = new ListNode<>(data, head);
    }

    public T getData(int index) {
        if (index > length) {
            throw new IllegalArgumentException("Индекс выходит за пределы длины списка" + length);
        }

        if (index == 0) {
            return head.getData();
        }

        ListNode<T> p = head;

        for (int i = 1; i <= index; i++) {
            p = p.getNext();
        }

        return p.getData();
    }

    public T setData(int index, T data) {
        if (index > length) {
            throw new IllegalArgumentException("Индекс выходит за пределы длины списка" + length);
        }

        if (index == 0) {
            return head.getData();
        }

        ListNode<T> p = head;

        for (int i = 1; i <= index; i++) {
            p = p.getNext();
        }

        return p.setData(data);
    }

    public void addElement(int index, T data) {
        if (index > length) {
            throw new IllegalArgumentException("Индекс выходит за пределы длины списка" + length);
        }

        if (index == 0) {
            head = new ListNode<>(data, head);
            return;
        }

        ListNode<T> p = head;

        for (int i = 1; i <= index; i++) {
            p = p.getNext();
        }

        p.setNext(new ListNode<>(data, p.getNext()));

    }

    public T deletePremierElement() {
        T old = head.getData();
        head = head.getNext();

        return old;
    }

    public boolean deleteElement(T t) {
        if (head.getData().equals(t)) {
            deletePremierElement();
            return true;
        }
        for (ListNode<T> p = head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
            if (p.getData().equals(t)) {
                prev.setNext(p.getNext());
                return true;
            }
        }
        return false;
    }

    public T deleteElement(int index) {
        if (index > length) {
            throw new IllegalArgumentException("Индекс выходит за пределы длины списка" + length);
        }
        if (index == 0) {
            return deletePremierElement();
        }
        ListNode<T> prev = head;
        ListNode<T> p = head.getNext();
        for (int i = 1; i <= index; i++) {
            prev = p;
            p = p.getNext();
        }
        T old = p.getData();
        prev.setNext(p.getNext());
        return old;
    }

    public void reversing() {
        ListNode<T> pPrev = null;
        ListNode<T> pNext;

        for (ListNode<T> p = head; p != null; ) {
            pNext = p.getNext();
            p.setNext(pPrev);
            pPrev = p;
            p = pNext;
        }
        head = pPrev;
    }

    public MyList<T> copyList() {
        MyList<T> ll = new MyList<>();
        ll.length = length;
        ListNode<T> item = new ListNode<>(head.getData());
        for (ListNode<T> p = head.getNext(); p != null; p = p.getNext()) {
            ListNode<T> itemNext = new ListNode<>(p.getData());
            item.setNext(itemNext);
            item = item.getNext();
        }
        return ll;
    }
}