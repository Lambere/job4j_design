package ru.job4j.collection;

import java.util.*;
import java.util.Iterator;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private Node<E> first;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (first == null) {
            first = newNode;
        } else {
            Node<E> current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
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
            private Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E item = current.item;
                current = current.next;
                return item;
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