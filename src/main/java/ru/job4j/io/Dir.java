package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Директория не существует: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Это не директория: %s", file.getAbsoluteFile()));
        }

        for (File subfile : file.listFiles()) {
            System.out.println("Размер файла: " + subfile.length());
            System.out.println("Имя файла: " + subfile.getName());
        }
    }
}

