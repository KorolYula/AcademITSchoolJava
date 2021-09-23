package academic.korol.hash_table;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private LinkedList<T>[] list;
   // private int hashTableSize;
    private int size;

    public MyHashTable(int hashTableSize) {
        if (hashTableSize <= 0) {
            throw new IllegalArgumentException("Размерность Хэш-таблицы > 0 ");
        }
        //noinspection unchecked
        list = (LinkedList<T>[]) new LinkedList[hashTableSize];
       // this.hashTableSize = hashTableSize;
        size = 0;
    }
//TODO правильно ли я зампенила
    public int GetHashCode(T t) {
        return Math.abs(t.hashCode() % list.length);
    }

    //TODO количество элементов в таблице
    @Override
    public int size() {
        return list.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
            for (int i = 0; i <= hashTableSize; i++) {
            list[i] = null;
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

                if (list[arrayIndex].size() == 0 || listIndex >= list[arrayIndex].size()) {
                    arrayIndex++;
                    listIndex = 0;
                }

                counter++;

                return list[arrayIndex].get(listIndex);
            }
        };
    }

    @Override
    public boolean contains(Object o) {
        int hashCode = GetHashCode((T) o);

        if (list[hashCode] == null) {
            return false;
        }

        for (T e : list[hashCode]) {
            if (e.equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean add(T t) {
        int hashCode = GetHashCode(t);

        if (list[hashCode] == null) {
            list[hashCode] = new LinkedList<T>();
            list[hashCode].add(t);
            elementCount++;
            return true;
        }

        for (T e : list[hashCode]) {
            if (e.equals(t)) {
                return false;
            }
        }

        list[hashCode].add(t);
        elementCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int hashCode = GetHashCode((T) o);

        if (list[hashCode] == null) {
            return false;
        }

        for (T e : list[hashCode]) {
            if (e.equals(o)) {
                list[hashCode].remove(o);
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



