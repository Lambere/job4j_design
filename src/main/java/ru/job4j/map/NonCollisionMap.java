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
        if (table[indexFor(hash(key.hashCode()))] == null) {
            table[indexFor(key.hashCode())] = new MapEntry<>(key, value);
        }

        count++;
        modCount++;
        capacity++;
        return table[indexFor(key.hashCode())] != null;
    }

    @Override
    public V get(K key) {
        K key1 = null;
        if (table[indexFor(hash(key.hashCode()))] == null) {
            key1 = table[indexFor(key.hashCode())].key;
        }
        return key == key1 ? null : table[indexFor(key.hashCode())].value;
    }

    @Override
    public boolean remove(K key) {
        count--;
        modCount--;
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                while (count < capacity) {
                    count++;
                }
                return count < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return this.next();
            }
        };
        }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
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


