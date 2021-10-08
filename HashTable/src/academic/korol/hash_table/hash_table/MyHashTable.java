package academic.korol.hash_table.hash_table;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private final LinkedList<T>[] lists;
    private int size;
    private final int defaultLength = 10;
    private int modCount;

    public MyHashTable(int listsLength) {
        if (listsLength <= 0) {
            throw new IllegalArgumentException("Длинна массива " + listsLength + "должна быть > 0 ");
        }
        //noinspection unchecked
        lists = (LinkedList<T>[]) new LinkedList[listsLength];
    }

    public MyHashTable() {
        //noinspection unchecked
        lists = (LinkedList<T>[]) new LinkedList[defaultLength];
    }

    private int getIndex(Object o) {
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

        for (int i = 0; i <= lists.length; i++) {
            if (lists[i] != null) {
                lists[i].clear();
            }
        }

        size = 0;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int counter = 0;
            private int arrayIndex = 0;
            private int listIndex = -1;
            private final int fixedModCount = modCount;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Больше нет элементов списка");
                }

                if (fixedModCount != modCount) {
                    throw new ConcurrentModificationException("Таблица была изменена!");
                }

                while (true) {
                    listIndex++;

                    if (lists[arrayIndex] == null || lists[arrayIndex].size() == 0 || listIndex >= lists[arrayIndex].size()) {
                        arrayIndex++;
                        listIndex = -1;
                    } else {
                        counter++;
                        return lists[arrayIndex].get(listIndex);
                    }
                }
            }
        };
    }

    @Override
    public boolean contains(Object o) {
        return lists[getIndex(o)] != null && lists[getIndex(o)].contains(o);
    }

    @Override
    public boolean add(T element) {
        int index = getIndex(element);

        if (lists[index] == null) {
            lists[index] = new LinkedList<>();
        }

        lists[index].add(element);
        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if ((lists[index] != null && lists[index].remove(o))) {
            size--;
            modCount++;
            return true;
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
        boolean isAdd = false;

        for (T element : c) {
            if (add(element)) {
                isAdd = true;
            }
        }

        return isAdd;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;

        for (Object element : c) {
            int index = getIndex(element);
            int oldListSize = lists[index].size();

            if (lists[index].removeAll(Collections.singleton(element))) {
                int newListSize = lists[index].size();
                size += newListSize - oldListSize;
                modCount++;
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRemoved = false;

        for (LinkedList<T> list : lists) {
            if (list != null) {
                int oldListSize = list.size();

                if (list.retainAll(c)) {
                    int newListSize = list.size();
                    size += newListSize - oldListSize;
                    modCount++;
                    isRemoved = true;
                }
            }
        }

        return isRemoved;
    }

    @Override
    public Object[] toArray() {
        Object[] objectsArray = new Object[size];
        int i = 0;

        for (T element : this) {
            objectsArray[i] = element;
            i++;
        }

        return objectsArray;
    }

    @Override
    public <E> E[] toArray(E[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (E[]) Arrays.copyOf(toArray(), size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, array, 0, size);
        array[size + 1] = null;

        return array;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < lists.length; i++) {
            stringBuilder.append("[");
            stringBuilder.append(i);
            stringBuilder.append("-");
            stringBuilder.append(lists[i]);
            stringBuilder.append("]");
        }

        return stringBuilder.toString();
    }
}