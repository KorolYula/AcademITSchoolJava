package academic.korol.hash_table;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private LinkedList<T>[] lists;
    private int size;
    private int myHashListSize = 10;
    private int modificationCounter;

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

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i <= myHashListSize; i++) {
            lists[i] = null;
        }
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int counter = 0;
            int arrayIndex = 0;
            int listIndex = -1;
            private final int fixModCount = modificationCounter;

            @Override
            public boolean hasNext() {
                return counter != size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (fixModCount != modificationCounter) {
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
        if (o == null) {
            return false;
        }
        int hashIndex = getHashIndex(o);

        if (lists[hashIndex] == null) {
            return false;
        }

        return lists[hashIndex].contains(o);
    }

    @Override
    public boolean add(T t) {
        if (t == null) {
            return false;
        }
        int hashIndex = getHashIndex(t);

        if (lists[hashIndex] == null) {
            lists[hashIndex] = new LinkedList<>();
        }

        lists[hashIndex].add(t);
        size++;
        modificationCounter++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }

        int hashIndex = getHashIndex(o);

        if (lists[hashIndex] == null) {
            return false;
        }

        if (lists[hashIndex].remove(o)) {
            size--;
            modificationCounter++;
            return true;
        }
        return false;
    }

    //DO правильно ли я за
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
        boolean isAdd = false;

        for (T element : c) {
            if (add(element)) {
                isAdd = true;
            }
        }

        return isAdd;
    }

    //TODO счетчик size нужно уменьшить на несколько раз. Как узнать на сколько?
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemove = false;

        for (Object element : c) {
            if (element != null) {
                int hashIndex = getHashIndex(element);

                if (lists[hashIndex].removeAll((Collection<?>) element)) {
                    size--;
                    modificationCounter++;
                    isRemove = true;
                }
            }
        }
        return isRemove;
    }

    //TODO счетчик size нужно уменьшить на несколько раз. Как узнать на сколько?
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isDelete = false;

        for (LinkedList<T> list : lists) {
            if (list.retainAll(c)) {
                size--;
                modificationCounter++;
                isDelete = true;
            }
        }

        return isDelete;
    }

    @Override
    public Object[] toArray() {
        Object[] objectsArray = new Object[size];
        int i = 0;

        for (T t : this) {
            if (t != null) {
                objectsArray[i] = t;
            }
            i++;
        }

        return objectsArray;
    }

    @Override
    public <E> E[] toArray(E[] array) {
        if (array.length >= size) {
            System.arraycopy(this.toArray(), 0, array, 0, size);

            for (int i = size; i < array.length; i++) {
                array[i] = null;
            }
            return array;
        }
        return (E[]) Arrays.copyOf(this.toArray(), size, array.getClass());
    }

    public String[] toStringHashTable() {
        if (size == 0) {
            return null;
        }
        String[] s = new String[myHashListSize];

        // StringBuilder stringBuilder = new StringBuilder();
        // stringBuilder.append("[");

        for (int i = 0; i < myHashListSize; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append(i);
            stringBuilder.append("-");
            if (lists[i] == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(lists[i].toString());
            }
            stringBuilder.append("]");
            s[i] = stringBuilder.toString();
        }
        return s;
    }


}