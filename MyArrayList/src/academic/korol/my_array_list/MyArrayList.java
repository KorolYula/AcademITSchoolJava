package academic.korol.my_array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] items;
    private int size;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость списка " + capacity + " должна быть >= 0 ");
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
            throw new IndexOutOfBoundsException("Индекс " + index + " должен быть не больше " + maxIndex);
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index, size - 1);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index, size - 1);

        E oldItem = items[index];
        items[index] = item;
        return oldItem;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object o : collection) {
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
            private final int fixedModCount = modCount;

            @Override
            public boolean hasNext() {
                return currentIndex + 1 < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Пройдены все элементы списка");
                }

                if (fixedModCount != modCount) {
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
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
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
    public boolean removeAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        boolean isChanged = false;

        for (int i = 0; i < size; i++) {
            if (collection.contains(items[i])) {
                //noinspection SuspiciousListRemoveInLoop
                remove(i);
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean add(E item) {
        add(size, item);
        return true;
    }

    @Override
    public void add(int index, E item) {
        checkIndex(index, size);

        if (size >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = item;
        size++;
        modCount++;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkIndex(index, size);

        if (collection.size() == 0) {
            return false;
        }

        ensureCapacity(size + collection.size());

        if (index < size) {
            System.arraycopy(items, index, items, index + collection.size(), size - index);
        }

        int i = index;
        for (E e : collection) {
            items[i] = e;
            i++;
        }

        size += collection.size();
        modCount++;

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean isChanged = false;

        for (int i = 0; i < size; i++) {
            if (!collection.contains(items[i])) {
                remove(i);
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

        Arrays.fill(items, 0);

        size = 0;
        modCount++;
    }

    private void increaseCapacity() {
        int newLength = (items.length != 0) ? items.length * 2 : 1;
        items = Arrays.copyOf(items, newLength);
    }

    @Override
    public E remove(int index) {
        checkIndex(index, size - 1);

        E removedItem = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[size - 1] = null;
        size--;
        modCount++;
        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
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

        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(items[i]);
            stringBuilder.append(", ");
        }

        stringBuilder.append(items[size - 1]).append("]");
        return stringBuilder.toString();
    }


    //В задании их не надо реализовывать ------------------------------------------------------------------

    @Override
    public ListIterator<E> listIterator() {
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        //noinspection ConstantConditions
        return null;
    }
}

