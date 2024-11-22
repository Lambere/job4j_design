package ru.job4j.io;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            String finish = "99:99:99";
            while (reader.ready()) {
                String[] second = reader.readLine().split(" ");
                map.put(second[1], second[0]);
            }
            for (String a : map.keySet()) {
                if (Integer.parseInt(map.get(a)) > 300 && sdf.parse(a).before(sdf.parse(finish))) {
                    finish = a;
                } else if (!finish.equals("99:99:99")) {
                    writer.println(finish + ";" + a);
                    finish = "99:99:99";
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
