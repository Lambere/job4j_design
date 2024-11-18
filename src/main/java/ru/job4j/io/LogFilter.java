package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public void saveTo(String out) {
        try {
            BufferedWriter reader = new BufferedWriter(new FileWriter(out));
            for (String a: filter()) {
                reader.append(a);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> filter() {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String readLine = reader.readLine();
                String[] a = readLine.split(" ");
                if ("404".equals(a[a.length - 2])) {
                    list.add(readLine);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}


