package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;
    private int count = 0;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    private void increaseOfArray() {
        if (count == container.length - 1 || container.length == 0) {
            container = Arrays.copyOf(container, (container.length + 1) * 2);
        }
    }

    @Override
    public void add(T value) {
        size++;
        modCount++;
        increaseOfArray();
        container[count++] = value;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        container[index] = newValue;
        return newValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        size--;
        modCount--;
        T[] containerCopy = (T[]) new Object[container.length - 1];
        System.arraycopy(container, index, containerCopy, 0, container.length - 1);
        for (int i = index; i < size + 1; i++) {
            container[i] = container[i + 1];
        }

        return containerCopy[0];
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int iPointer = 0;
            final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return container.length > iPointer && container[iPointer] != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    if (modCount != expectedModCount) {
                        throw new ConcurrentModificationException();
                    } else {
                        return container[iPointer++];
                    }
                }
            }
        };
    }
}
