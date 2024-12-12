package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.containsKey(key)) {
            return values.get(key);
        } else {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
    }

    private void parse(String arg) {
            String[] a =  arg.split("=", 2);
            a[0] = a[0].replace("-", "");
            values.put(a[0], a[1]);
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        if (args.length  == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            names.checkForValidation(arg);
            names.parse(arg);
        }
        return names;
    }

    private void checkForValidation(String arg) {
        String res = null;
        if (arg.startsWith("-=")) {
            res = ("Error: This argument '" + arg + "' does not contain a key");
        } else if (!arg.startsWith("-")) {
                res = "Error: This argument '"+ arg + "' does not start with a '-' character";
            } else  if (!arg.contains("=")) {
                    res = "Error: This argument '"+ arg + "' does not contain an equal sign";
                } else if (arg.split("=").length < 2) {
                    res = "Error: This argument '"+ arg + "' does not contain a value";
                }
        if (res != null) {
            throw new IllegalArgumentException(res);
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512==", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
