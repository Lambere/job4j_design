package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int j = 0;
        while (source.hasNext()) {
            nodes.get(j).add(source.next());
            j++;
            if (j == nodes.size()) {
                j = 0;
            }
        }

    }
}
