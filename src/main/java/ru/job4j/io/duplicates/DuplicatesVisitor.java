package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    HashSet<FileProperty> elements = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {

        String[] a = file.getFileName().toString().split("/");
        FileProperty fileProperty = new FileProperty(attributes.size(), a[a.length - 1]);
        if (elements.contains(fileProperty)) {
            System.out.println(fileProperty.getName());
        }
        elements.add(fileProperty);

        return super.visitFile(file, attributes);
    }
}
