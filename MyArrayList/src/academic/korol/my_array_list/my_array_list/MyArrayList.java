package academic.korol.my_array_list.my_array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;
    private final int initialCapacity = 10;

    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[initialCapacity];
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Размерность списка " + capacity + " должна быть >= 0 ");
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private static void checkIndex(int index, int maxIndex) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0, а сейчас " + index);
        }

        if (index > maxIndex) {
            throw new IndexOutOfBoundsException("Индекс " + index + "должен быть не больше" + maxIndex);
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index, size - 1);
        return items[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index, size);
        E oldElement = items[index];
        items[index] = element;
        return oldElement;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
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
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int currentIndex = -1;
            private final int fixModCount = modCount;

            @Override
            public boolean hasNext() {
                return currentIndex + 1 < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (fixModCount != modCount) {
                    throw new ConcurrentModificationException("Список был изменен!");
                }

                ++currentIndex;
                return items[currentIndex];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length >= size) {
            System.arraycopy(items, 0, array, 0, size);

            for (int i = size; i < array.length; i++) {
                array[i] = null;
            }

            return array;
        }

        return (T[]) Arrays.copyOf(items, size, array.getClass());
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index < 0) {
            return false;
        }

        remove(index);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isDeleted = false;

        for (E e : items) {
            if (c.contains(e)) {
                remove(e);
                isDeleted = true;
            }
        }

        return isDeleted;
    }

    @Override
    public boolean add(E element) {
        add(size, element);
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index, size + 1);

        if (size >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = element;
        size++;
        modCount++;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkIndex(index, size + 1);

        ensureCapacity(size + collection.size());
        if (index < size) {
            System.arraycopy(items, index, items, index + collection.size(), size - index);
        }
        System.arraycopy(items, index, collection, 0, collection.size());

        size += collection.size();
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean isChanged = false;

        for (E element : items) {
            if (!collection.contains(element)) {
                remove(element);
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
        modCount++;
    }

    private void increaseCapacity() {
        int newSize = (items.length != 0) ? items.length * 2 : 1;
        items = Arrays.copyOf(items, newSize);
    }

    @Override
    public E remove(int index) {
        checkIndex(index, size);

        E removedItem = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[size - 1] = null;
        --size;
        modCount++;
        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i],o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i],o)) {
                return i;
            }
        }

        return -1;
    }

    public void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (int i = 0; ; i++) {
            stringBuilder.append(items[i]);
            if (i == size - 1) {
                return stringBuilder.append("]").toString();
            }
            stringBuilder.append(", ");
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

