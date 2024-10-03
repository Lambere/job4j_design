package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int size;

    public T poll() {
        int sizeCopy = size;
        while (--sizeCopy >= 0) {
            input.push(output.pop());
        }
        if (input == null) {
            throw new NoSuchElementException("Queue is empty");
        }
        size--;
        return input.pop();
    }

    public void push(T value) {
        size++;
        output.push(value);
    }

}
