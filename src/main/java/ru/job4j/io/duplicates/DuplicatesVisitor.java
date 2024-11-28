package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(attributes.size(), file.getFileName().toString())
        Predicate predicate = path -> path;
        if (predicate.test(fileProperty)) {
            System.out.println(file.toAbsolutePath());
        }

        return super.visitFile(file, attributes);
    }
}
