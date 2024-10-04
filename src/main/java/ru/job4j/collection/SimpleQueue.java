package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int size;
    private int inputSize;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        int sizeCopy = size;
        if (inputSize == 0) {
            while (--sizeCopy >= 0) {
                input.push(output.pop());
                inputSize++;
            }
        }
        size--;
        inputSize--;
        return input.pop();
    }

    public void push(T value) {
        size++;
        output.push(value);
    }
}
