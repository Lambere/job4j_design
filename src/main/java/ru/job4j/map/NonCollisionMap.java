package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean res = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        if (table[findIndex(key)] == null) {
            res = true;
            table[findIndex(key)] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }

        return res;
    }

    @Override
    public V get(K key) {
        V res = null;
        MapEntry<K, V> entry = table[findIndex(key)];
        if (checkKey(key)) {
            res = entry.value;
        }
        return res;
    }

    private boolean checkKey(K key) {
        MapEntry<K, V> object = table[findIndex(key)];
        return object != null && Objects.hashCode(key) == Objects.hashCode(object.key)
                && Objects.equals(object.key, key);
    }

    @Override
    public boolean remove(K key) {
        boolean res = false;
        if (checkKey(key)) {
            table[findIndex(key)] = null;
            count--;
            modCount++;
            res = true;
        }
        return res;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && (table[index] == null)) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private int findIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        count = 0;
        for (MapEntry<K, V> entry : oldTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
        modCount++;
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


