package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            String a = reader.readLine();
            String[] values = a.split("=");
            if (a.endsWith("=")) {
                values[1] += "=";
            }
            validate(values[0], values[1]);
            if (values.length > 2) {
                for (int i = 2; i < values.length; i++) {
                    values[1] += "=" + values[i];
                }
            }
                this.values.put(values[0], values[1]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void validate(String a, String b) {
        if (a.isEmpty() || b.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("src/data/app.properties"));
    }

}

