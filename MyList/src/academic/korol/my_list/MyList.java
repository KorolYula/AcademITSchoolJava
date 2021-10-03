package academic.korol.my_list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class MyList<T> {
    private ListNode<T> head;
    private int length;

    public MyList() {
    }

    public MyList(T data) {
        length = 1;
        head = new ListNode<>(data);
    }

    public int getLength() {
        return length;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст!");
        }

        return head.getData();
    }

    public void addFirst(T data) {
        head = new ListNode<>(data, head);
        length++;
    }

    private void checkIndex(int index, int maxPossibleIndex) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс " + index + " должен быть >= 0");
        }

        if (index > maxPossibleIndex) {
            throw new IndexOutOfBoundsException("Индекс " + index + "  должен быть <= " + maxPossibleIndex);
        }

    }

    private ListNode<T> getNode(int index) {
        checkIndex(index, length);

        ListNode<T> node = head;

        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }

        return node;
    }

    public T getData(int index) {
        return getNode(index).getData();
    }

    public T setData(int index, T data) {
        return getNode(index).setData(data);
    }

    public void add(int index, T data) {
        checkIndex(index, length + 1);

        if (index == 0) {
            addFirst(data);
            return;
        }

        getNode(index - 1).setNext(new ListNode<>(data, getNode(index - 1).getNext()));
        length++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст!");
        }

        T deletedData = head.getData();
        head = head.getNext();
        length--;

        return deletedData;
    }

    public boolean delete(T data) {
        if (head == null) {
            return false;
        }

        if (Objects.equals(head.getData(), data)) {
            deleteFirst();
            return true;
        }

        ListNode<T> currentNode = head.getNext();
        ListNode<T> previousNode = head;

        for (int i = 1; i < length; i++) {
            if ((data == null && currentNode.getData() == null) || (currentNode.getData() != null && Objects.equals(currentNode.getData(), data))) {
                previousNode.setNext(currentNode.getNext());
                length--;
                return true;
            }

            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        return false;
    }

    public T delete(int index) {
        checkIndex(index, length);

        if (index == 0) {
            return deleteFirst();
        }

        T deletedData = getNode(index).getData();
        getNode(index - 1).setNext(getNode(index).getNext());
        length--;
        return deletedData;
    }

    public void reverse() {
        ListNode<T> previousNode = null;

        for (ListNode<T> currentNode = head; currentNode != null; ) {
            ListNode<T> nextNode = currentNode.getNext();
            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
        }

        head = previousNode;
    }

    public MyList<T> copy() {
        if (length == 0) {
            return new MyList<>();
        }

        MyList<T> newList = new MyList<>(head.getData());
        newList.length = length;

        for (ListNode<T> currentNode = head.getNext(), newNode = newList.head; currentNode != null; currentNode = currentNode.getNext()) {
            ListNode<T> nextNode = new ListNode<>(currentNode.getData());
            newNode.setNext(nextNode);
            newNode = newNode.getNext();
        }

        return newList;
    }

    public String toString() {
        if (length == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (ListNode<T> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            stringBuilder.append(currentNode.getData());
            stringBuilder.append(", ");
        }

        stringBuilder.setCharAt(stringBuilder.length() - 2, ']');
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }
}