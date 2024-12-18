package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        search(Path.of(args[0]), path -> path.toFile().getName().endsWith("." + args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {

        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        Path file = Path.of(args[0]);
        if (!file.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.toAbsolutePath()));
        }
        if (!file.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.toAbsolutePath()));
        }
    }
}
