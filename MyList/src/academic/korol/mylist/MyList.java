package academic.korol.mylist;

public class MyList<T> {
    private ListNode<T> head;
    private int length;

    public MyList() {
        length = 1;
        head = null;
    }

    public MyList(T data) {
        if (data == null) {
            throw new NullPointerException("Список пуст!");
        }
        length = 1;
        head = new ListNode<>(data);
    }

    public int getLength() {
        return length;
    }

    public T getFirst() {
        if (head == null) {
            throw new NullPointerException("Список пуст!");
        }
        return head.getData();
    }

    public void addFirst(T data) {
        head = new ListNode<>(data, head);
        length++;
    }

    public void checkIndex(int index, int maximumPossibleIndex) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >=0, а сейчас " + index);
        }

        if (index > maximumPossibleIndex) {
            throw new IndexOutOfBoundsException("Индекс " + index + "выходит за пределы длины списка " + length);
        }
    }

    public T getData(int index) {
        checkIndex(index, length - 1);

        if (index == 0) {
            return head.getData();
        }

        ListNode<T> node = head;

        for (int i = 1; i <= index; i++) {
            node = node.getNext();
        }

        return node.getData();
    }

    public T setData(int index, T data) {
        checkIndex(index, length);

        if (index == 0) {
            return head.getData();
        }

        ListNode<T> node = head;

        for (int i = 1; i <= index; i++) {
            node = node.getNext();
        }

        return node.setData(data);
    }

    public void addNode(int index, T data) {
        checkIndex(index, length);

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListNode<T> node = head;

        for (int i = 1; i < index; i++) {
            node = node.getNext();
        }

        node.setNext(new ListNode<>(data, node.getNext()));
        length++;

    }

    public T deleteFirst() {
        if (head == null) {
            throw new NullPointerException("Список пуст!");
        }

        T oldData = head.getData();
        head = head.getNext();
        length--;

        return oldData;
    }

    public boolean deleteNode(T data) {
        if (head == null) {
            throw new NullPointerException("Список пуст!");
        }

        if (data == null) {
            return false;
        }

        if (head.getData().equals(data)) {
            deleteFirst();
            return true;
        }

        for (ListNode<T> node = head.getNext(), previousNode = head; node != null; previousNode = node, node = node.getNext()) {
            if (node.getData().equals(data)) {
                previousNode.setNext(node.getNext());
                length--;
                return true;
            }
        }

        return false;
    }

    public T deleteNode(int index) {
        checkIndex(index, length - 1);

        if (index == 0) {
            return deleteFirst();
        }

        ListNode<T> previousNode = head;
        ListNode<T> currentNode = head.getNext();

        for (int i = 1; i <= index; i++) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        T oldData = currentNode.getData();
        previousNode.setNext(currentNode.getNext());
        length--;
        return oldData;
    }

    public void revers() {
        ListNode<T> previousNode = null;
        ListNode<T> nextNode;

        for (ListNode<T> currentNode = head; currentNode != null; ) {
            nextNode = currentNode.getNext();
            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
        }

        head = previousNode;
    }

    public MyList<T> copy() {
        MyList<T> newList = new MyList<>(head.getData());
        newList.length = length;

        for (ListNode<T> currentNode = head.getNext(), newNode = newList.head; currentNode != null; currentNode = currentNode.getNext()) {
            ListNode<T> itemNext = new ListNode<>(currentNode.getData());
            newNode.setNext(itemNext);
            newNode = newNode.getNext();
        }

        return newList;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (ListNode<T> currentNode = head; currentNode != null;currentNode=currentNode.getNext() ) {
            stringBuilder.append ("( ");
            stringBuilder.append(i);
            stringBuilder.append (" - ");
            stringBuilder.append(currentNode.getData());
            stringBuilder.append (") ");
            i++;
        }
        return stringBuilder.toString();
    }


}