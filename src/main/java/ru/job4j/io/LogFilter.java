package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String readLine = reader.readLine();
                String[] a = readLine.split(" ");
                if (a[a.length - 2].equals("404")) {
                    list.add(readLine);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data\\log.txt");
        logFilter.filter().forEach(System.out::println);

    }
}
