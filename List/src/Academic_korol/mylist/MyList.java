package Academic_korol.mylist;

import java.util.Arrays;
import java.util.List;

public class MyList<E> implements List<E> {
    private E[] items;
    private int length;

    public MyList(int length) { //T[] value) {
        if (length <= 0) {
            throw new IllegalArgumentException("Размерность списка должна быть > 0, а сейчас  " + length);
        }
        this.items[length] = new E[length];
        this.length = length;
    }

    public int Count() {
        return length;
    }

    public E getE(int index) {
        if (index >= Count()) {
            throw new IllegalArgumentException("Выход за длину списка");
        }
        return this.items[index];
    }

    public void setE(int index, E value) {
        if (index >= Count()) {
            throw new IllegalArgumentException("Выход за длину списка");
        }
        items[index] = value;
    }

    public void Add(E obj) {
        if (length >= items.length) {
            IncreaseCapacity();
        }
        items[length] = obj;
        ++length;
    }

    private void IncreaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void removeE(int index) {
        if (index >= Count()) {
            throw new IllegalArgumentException("Выход за длину списка");
        }
        if (index < length - 1) {
            System.arraycopy(items, index + 1, items, index, length - index - 1);
        }
        --length;
    }
}
            }




