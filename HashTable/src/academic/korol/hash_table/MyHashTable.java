package academic.korol.hash_table;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private LinkedList<T>[] items;
    private int hashTableSize;
    private int elementCount;

    public MyHashTable(int hashTableSize) {
        if (hashTableSize <= 0) {
            throw new IllegalArgumentException("Размерность Хэш-таблицы > 0 ");
        }
        //noinspection unchecked
        items = (LinkedList<T>[]) new LinkedList[hashTableSize];
        this.hashTableSize = hashTableSize;
        elementCount = 0;
    }

    public int GetHashCode(T t) {
        return Math.abs(t.hashCode() % hashTableSize);
    }

    @Override
    public int size() {
        return hashTableSize;
    }

    @Override
    public boolean isEmpty() {
        return elementCount == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i <= hashTableSize; i++) {
            items[i] = null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int counter = 0;
            int arrayIndex = 0;
            int listIndex = -1;

            @Override
            public boolean hasNext() {
                return counter != elementCount;
            }

            @Override
            public T next() {
                listIndex++;

                if (items[arrayIndex].size() == 0 || listIndex >= items[arrayIndex].size()) {
                    arrayIndex++;
                    listIndex = 0;
                }

                counter++;

                return items[arrayIndex].get(listIndex);
            }
        };
    }

    @Override
    public boolean contains(Object o) {
        int hashCode = GetHashCode((T) o);

        if (items[hashCode] == null) {
            return false;
        }

        for (T e : items[hashCode]) {
            if (e.equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean add(T t) {
        int hashCode = GetHashCode(t);

        if (items[hashCode] == null) {
            items[hashCode] = new LinkedList<T>();
            items[hashCode].add(t);
            elementCount++;
            return true;
        }

        for (T e : items[hashCode]) {
            if (e.equals(t)) {
                return false;
            }
        }

        items[hashCode].add(t);
        elementCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int hashCode = GetHashCode((T) o);

        if (items[hashCode] == null) {
            return false;
        }

        for (T e : items[hashCode]) {
            if (e.equals(o)) {
                items[hashCode].remove(o);
                elementCount--;
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
    public boolean addAll(Collection<? extends T> c) {
        int additionsCount = 0;

        for (T element : c) {
            if (add(element)) {
                additionsCount++;
            }
        }

        return additionsCount == 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int deletionsCount = 0;

        for (Object element : c) {
            if (remove(element)) {
                deletionsCount++;
            }
        }

        return deletionsCount == 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int count = 0;

        for (Iterator<T> i = iterator(); i.hasNext(); ) {
            if (!c.contains(i)) {
                remove(i);
                count++;
            }
        }

        return count > 0;
    }


    @Override
    public Object[] toArray() {
        Object[] o = new Object[elementCount - 1];
        int index = 0;

        for (Iterator<T> i = iterator(); i.hasNext(); ) {
            o[index] = i;
            index++;
        }

        return o;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length >= elementCount) {
            int index = 0;

            for (Iterator<T> i = iterator(); i.hasNext(); ) {
                a[index] = (E) i;
                index++;
            }

            for (int j = index + 1; j < a.length; j++) {
                a[j] = null;
            }

            return a;
        }

        E[] array = (E[]) new Object[elementCount];
        int index = 0;

        for (Iterator<T> i = iterator(); i.hasNext(); ) {
            array[index] = (E) i;
            index++;
        }

        return array;
    }
}



