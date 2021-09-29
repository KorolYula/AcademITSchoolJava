package academic.korol.hash_table;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private LinkedList<T>[] lists;
    // private int hashTableSize;
    private int size;
    private int myHashListSize = 20;
    private int modificationСounter;

    public MyHashTable(int hashListSize) {
        if (hashListSize <= 0) {
            throw new IllegalArgumentException("Размерность Хэш-таблицы " + hashListSize + "должна быть > 0 ");
        }
        lists = (LinkedList<T>[]) new LinkedList[hashListSize];
        myHashListSize = hashListSize;
    }

    public MyHashTable() {
        lists = (LinkedList<T>[]) new LinkedList[myHashListSize];
    }

    public int getHashIndex(Object o) {
        return o != null ? Math.abs(o.hashCode() % lists.length) : 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //TODO правильно ли я за
    @Override
    public void clear() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i <= myHashListSize; i++) {
            lists[i] = null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int counter = 0;
            int arrayIndex = 0;
            int listIndex = -1;
            private final int fixModCount = modificationСounter;

            @Override
            public boolean hasNext() {
                return counter != size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (fixModCount != modificationСounter) {
                    throw new ConcurrentModificationException("Таблица была изменена!");
                }

                listIndex++;

                if (lists[arrayIndex] == null || lists[arrayIndex].size() == 0 || listIndex >= lists[arrayIndex].size()) {
                    arrayIndex++;
                    listIndex = 0;
                }

                counter++;

                return lists[arrayIndex].get(listIndex);
            }
        };
    }

    //TODO правильно ли я за
    @Override
    public boolean contains(Object o) {
        int hashIndex = getHashIndex(o);

        if (lists[hashIndex] == null) {
            return false;
        }

        return lists[hashIndex].contains(o);
    }

    //TODO правильно ли я за
    @Override
    public boolean add(T t) {
        int hashIndex = getHashIndex(t);

        if (lists[hashIndex] == null) {
            lists[hashIndex] = new LinkedList<T>();
            lists[hashIndex].add(t);
            size++;
            modificationСounter++;
            return true;
        }

        if (lists[hashIndex].contains(t)) {
            return false;
        }


    lists[hashIndex].add(t);

    size++;
    modificationСounter++;
        return true;
}

    //TODO правильно ли я за
    @Override
    public boolean remove(Object o) {
        int hashIndex = getHashIndex(o);

        if (lists[hashIndex] == null) {
            return false;
        }

        for (T e : lists[hashIndex]) {
            if (e.equals(o)) {
                lists[hashIndex].remove(o);
                size--;
                modificationСounter++;
                return true;
            }
        }

        return false;
    }

    //TODO правильно ли я за
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    //TODO правильно ли я за
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

    //TODO правильно ли я за
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

    //TODO правильно ли я за
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

    //TODO правильно ли я за
    @Override
    public Object[] toArray() {
        Object[] o = new Object[size - 1];
        int index = 0;

        for (Iterator<T> i = iterator(); i.hasNext(); ) {
            o[index] = i;
            index++;
        }

        return o;
    }

    //TODO правильно ли я за
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



