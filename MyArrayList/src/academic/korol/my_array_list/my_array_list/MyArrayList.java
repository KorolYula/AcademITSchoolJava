package academic.korol.my_array_list.my_array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[10];
        modCount = 0;
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Размерность списка должна быть >= 0 ");
        }
        //noinspection unchecked
        items = (E[]) new Object[capacity];
        modCount = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void checkIndex(int index, int maxIndex) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >=0, а сейчас " + index);
        }

        if (index >= maxIndex) {
            throw new IndexOutOfBoundsException("Индекс " + index + "должен быть меньше" + maxIndex);
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index, items.length);
        return items[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index, items.length);
        E old = items[index];
        items[index] = element;
        return old;
    }

    @Override
    public boolean contains(Object o) {
        return (indexOf(o) >= 0);
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
                ++currentIndex;

                if (currentIndex >= size) {
                    throw new NoSuchElementException();
                }

                if (fixModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                return items[currentIndex];
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        System.arraycopy(items, 0, array, 0, size);
        return array;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length >= size) {
            //noinspection unchecked
            array = (T[]) Arrays.copyOf(items, size, array.getClass());
            return array;
        }
        //noinspection unchecked ,UnusedAssignment
        T[] newArray = (T[]) new Object[size];
        //noinspection unchecked
        newArray = (T[]) Arrays.copyOf(items, size, array.getClass());
        return newArray;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index < 0) {
            return false;
        }

        System.arraycopy(items, index + 1, items, index, size - index - 1);
        size--;
        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int result = 0;

        for (Object o : c) {
            if (remove(o)) {
                result++;
            }
        }

        return result > 0;
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

        if (index <= size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = element;
        size++;
        modCount++;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        addAll(size, collection);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkIndex(index, items.length);

        ensureCapacity(size + collection.size());

        System.arraycopy(items, index, items, index + collection.size(), size - index);
        int insertIndex = index;

        for (E e : collection) {
            add(insertIndex, e);
            insertIndex++;
        }

        size += collection.size();
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int count = 0;

        for (E element : items) {
            if (!collection.contains(element)) {
                remove(element);
                count++;
            }
        }

        return count > 0;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (E e : items) {
            e = null;
            modCount++;
        }
    }

    private void increaseCapacity() {
        int newSize = items.length * 2;

        if (items.length == 0) {
            newSize = 1;
        }

        items = Arrays.copyOf(items, newSize);
        modCount++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index, size);

        E deletedItem = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, items.length - index - 1);
        }

        items[size - 1] = null;
        --size;
        modCount++;
        return deletedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (items[i] != null && items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (items[i] != null && items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    public void ensureCapacity(int capacity) {
        if (capacity >= items.length) {
            items = Arrays.copyOf(items, capacity);
            modCount++;
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
            modCount++;
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

