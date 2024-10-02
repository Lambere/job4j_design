package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    private int size;

    public T poll() {
        int sizeCopy = size;
        while (sizeCopy-- >= 2) {
             input.push(output.pop());
        }
        size--;
        return output.pop();
    }

    public void push(T value) {
        output.push(value);
        size++;
    }
}
