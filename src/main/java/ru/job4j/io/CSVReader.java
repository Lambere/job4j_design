package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Stream;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        int countOfAddedEl = 0;
        int u = 0;

        String delimiter = argsName.get("delimiter");
        StringBuilder builder = new StringBuilder();
        var list = new ArrayList<Integer>();
        String[] filter = argsName.get("filter").split(",");

        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path"))).
                useDelimiter(delimiter)) {
            var main = new ArrayList<>(Arrays.asList(scanner.nextLine().split(delimiter)));
            var array1 = main;
            Stream.of(filter).forEach(x -> list.add(main.indexOf(x)));
                int j = list.get(0);
                for (int i = 0; true; i++) {
                    if (i == array1.size() && u == array1.size()) {
                        u = 0;
                        i = -1;
                    }
                    if (countOfAddedEl == filter.length) {
                        array1 = new ArrayList<>(Arrays.asList(scanner.nextLine().split(delimiter)));
                        countOfAddedEl = 0;
                        builder.append(System.lineSeparator());
                        i = -1;
                    }
                    if (j == i) {
                        ++u;
                        builder.append(array1.get(i));
                        if (u >= list.size()) {
                            u = 0;
                        } else {
                            builder.append(delimiter);
                        }
                        j = list.get(u);
                        countOfAddedEl++;
                        i = -1;
                    }
                    if (!scanner.hasNext() && countOfAddedEl == list.size()) {
                        builder.append(System.lineSeparator());
                        break;
                    }
                }
                writeInto(builder, argsName);
        }
    }

    public static void writeInto(StringBuilder builder, ArgsName argsName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(argsName.get("out")))) {
            writer.write(builder.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws Exception {
        if (args != null) {
            ArgsName argsName = ArgsName.of(args);
            handle(argsName);
        }
    }
}