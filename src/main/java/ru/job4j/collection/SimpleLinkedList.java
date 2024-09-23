package ru.job4j.collection;

import java.util.*;
import java.util.Iterator;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int modCount = 0;
    private int size;
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (last == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> a = first;
        for (int i = 0; index > i; i++) {
            a = a.next;
        }
            return a.item;

    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                currentIndex++;
                Node<E> lastReturned = first;
                first = first.next;
                return lastReturned.item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}