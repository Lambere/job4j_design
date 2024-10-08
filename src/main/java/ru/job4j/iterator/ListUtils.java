package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        addBefore(list, index + 1, value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
                list.removeIf(filter::equals);
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();

        while (iterator.hasNext()) {
            if (filter.equals(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            for (T i : elements) {
                if (iterator.next().equals(i)) {
                    iterator.remove();
                }
            }
        }
    }
}
