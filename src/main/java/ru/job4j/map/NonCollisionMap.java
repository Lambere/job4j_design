package ru.job4j.map;

import ru.job4j.collection.SimpleLinkedList;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    public static void main(String[] args) {
        NonCollisionMap a = new NonCollisionMap();
        System.out.println(a.indexFor(6));
    }

    @Override
    public boolean put(K key, V value) {
        boolean res = false;
        if (table[indexFor(hash(key.hashCode()))] == null) {
            table[indexFor(hash(key.hashCode()))] = new MapEntry<>(key, value);
            count++;
            modCount++;
            res = true;
        }

        return res;
    }

    @Override
    public V get(K key) {
        V res = null;
        if (table[indexFor(hash(key.hashCode()))].value == null) {
            res = table[indexFor(hash(key.hashCode()))].value;
        }
        return res;
    }

    @Override
    public boolean remove(K key) {

        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                while (count < capacity && table[count] == null) {
                    count++;
                }
                return count < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[count].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & capacity - 1;
    }

    private void expand() {
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}


