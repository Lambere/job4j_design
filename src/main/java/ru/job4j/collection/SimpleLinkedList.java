package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    int modCount = 0;
    int size = 0;
    Node<E> first;
    Node<E> last;

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(value, l);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
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

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int i = 0;
            Node<E> res = first;
            final int expectedModCount = modCount;
            Node<E> first1 = first;
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return res != null && i < size;
            }

            @Override
            public E next() {

                if (!hasNext()) {
                    i = 0;
                    throw new NoSuchElementException();
                } else {
                    i++;
                    if (modCount != expectedModCount) {
                        throw new ConcurrentModificationException();
                    } else {
                        if (res == first1) {
                            first1 = last;
                            return res.item;
                        }
                        return res.next.item;
                    }
                }
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