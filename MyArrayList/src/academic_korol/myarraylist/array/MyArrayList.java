package academic_korol.myarraylist.array;

import java.util.*;

public class MyArrayList<E> implements List<E> {

    private E[] items;
    private int length;


    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Размерность списка должна быть > 0 ");
        }
        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (E e : items) {
            if (e.equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < length;
            }

            @Override
            public E next() {
                return items[currentIndex++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] o = new Object[length];
        System.arraycopy(items, 0, o, 0, length);
        return o;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length >= length) {
            System.arraycopy(items, 0, a, 0, length);
            return a;
        }
        //noinspection unchecked
        E[] e = (E[]) new Object[length];
        System.arraycopy(items, 0, e, 0, length);
        return e;
    }

    @Override
    public boolean add(E element) {
        if (length >= items.length) {
            IncreaseCapacity();
        }
        items[length] = element;
        length++;
        return contains(element);
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < length; i++) {
            if (items[i].equals(o)) {
                System.arraycopy(items, i + 1, items, i, items.length - i - 1);
                items[length] = null;
                length--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (length + c.size() >= items.length) {
            IncreaseCapacity();
        }
        for (E element : c) {
            add(element);
        }
        return containsAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (length + c.size() >= items.length) {
            IncreaseCapacity();
        }
        System.arraycopy(items, index, items, index + c.size(), length - index);
        System.arraycopy(c, 0, items, index, c.size());

        length += c.size();
        return containsAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            if (!remove(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int count = 0;
        for (E element : items) {
            if (!c.contains(element)) {
                remove(element);
                count++;
            }
        }
        return count > 0;
    }

        @Override
    public void clear() {
        length = 0;
        items = null;
    }

    @Override
    public E get(int index) {
        if (index >= items.length) {
            throw new IllegalArgumentException("Выход за длину списка");
        }
        return this.items[index];
    }

    @Override
    public E set(int index, E element) {
        if (index >= length) {
            throw new IllegalArgumentException("Выход за длину списка");
        }
        E old = items[index];
        items[index] = element;
        return old;
    }

    private void IncreaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public void add(int index, E element) {
        if (index >= length) {
            throw new IllegalArgumentException("Выход за длину списка");
        }
        if (length >= items.length) {
            IncreaseCapacity();
        }
        System.arraycopy(items, index, items, index + 1, length - index);

        items[index] = element;
        length++;
    }

    @Override
    public E remove(int index) {
        if (index >= length) {
            throw new IllegalArgumentException("Выход за длину списка");
        }
        E old = items[index];
        if (index < length - 1) {
            System.arraycopy(items, index + 1, items, index, items.length - index - 1);
        }
        items[length - 1] = null;
        --length;
        return old;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (items[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
            if (items[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public void ensureCapacity(int capacity) {
        if (capacity >= items.length) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (length < items.length) {
            items = Arrays.copyOf(items, length);
        }
    }

   //В задании их не надо реализовывать ------------------------------------------------------------------
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}



