package Academic_korol.mylist.mylist.mylist;

import java.util.Arrays;
import java.util.List;

public class MyList<T> {//implements List<E> {
    private T data;
    private MyList<T> next;

//TODO не было задачи сделать конструктор
    public MyList(int capacity) { //T[] value) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Размерность списка должна быть > 0, а сейчас  " + length);
        }
        this.items[capacity] = new E[capacity];
        this.capacity = capacity;
    }

    public int getLength() {
        return items.length;
    }

    public E getPremierElement() {
        return this.items[0];
    }

    public E getE(int index) {
        if (index >= items.length) {
            throw new IllegalArgumentException("Выход за длину списка");
        }
        return this.items[index];
    }

    public E setE(int index, E value) {
        if (index >= items.length) {
            throw new IllegalArgumentException("Выход за длину списка");
        }
        E old= items[index];
        items[index] = value;
        return old;
    }

    public void AddPremierElement(E obj) {
        if (items.length >= length) {
            throw new IllegalArgumentException("Выход за длину списка");
        }
        E old= items[index];
        if (index < items.length - 1) {
            System.arraycopy(items, index + 1, items, index, length - index - 1);
        }
        --length;

        items[length] = obj;
        ++length;
    }


    public void Add(int index, E obj) {

        if (length >= items.length) {
            IncreaseCapacity();
        }
        items[length] = obj;
        ++length;
    }

    private void IncreaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public E removeE(int index) {
        if (index >= items.length) {
            throw new IllegalArgumentException("Выход за длину списка");
        }
        E old= items[index];
        if (index < items.length - 1) {
            System.arraycopy(items, index + 1, items, index, items.length - index - 1);
        }
        --items.length;
        items[items.length]=null;
        return  old;
    }
}





