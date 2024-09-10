package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    private int index;

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean result = index - 1 < data.size();
        if (!data.isEmpty()) {
            result = false;
        }
        return result;
    }

    @Override
    public T next() {
        index++;
        if (data.isEmpty()) {
            throw  new NoSuchElementException();
        }
        if (!hasNext()) {
            index = 1;
        }
        return data.get(index - 1);
    }
}
