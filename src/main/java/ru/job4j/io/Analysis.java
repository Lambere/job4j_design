package ru.job4j.io;

import java.io.*;
import java.text.SimpleDateFormat;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            String finish = "99:99:99";
            while (reader.ready()) {
                String[] a = reader.readLine().split(" ");
                if (Integer.parseInt((a[0])) > 300) {
                    if (sdf.parse(a[1]).before(sdf.parse(finish))) {
                        finish = a[1];
                    }
                } else if (!finish.equals("99:99:99")) {
                    writer.println(finish + ";" + a[1]);
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
