package Academic_korol.mylist.mylist.mylist;

import java.util.Arrays;


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

    public void AddPremierElement(T data) {
        head = new ListNode<>(data, head);
    }

    public T DeletePremierElement() {
        T old = head.getData();
        head = head.getNext();
        return old;
    }


    public boolean DeleteElement(T t) {
        ListNode<T> prev = null;

        for (ListNode<T> p = head ; p != null; p = p.getNext()) {
            if (p.getData().equals(t)){
                prev.setNext(p.getNext());
                return true;
            }
            prev = p;
        }
        return false;
    }

    public T DeleteElement(int index) {
        if (index > length) {
            throw new IllegalArgumentException("Индекс выходит за пределы длины списка" + length);
        }
        if (index==0) {
            return DeletePremierElement();
        }

        ListNode<T> prev = null;
        ListNode<T> p = head;
        for (int count=1;count<=index;count++) {
            prev = p;
            p = p.getNext();

        }
            if (p.getData().equals(t)){
                prev.setNext(p.getNext());

            }
            prev = p;
        }

    }

}





