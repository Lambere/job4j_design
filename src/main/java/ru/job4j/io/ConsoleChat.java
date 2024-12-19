package ru.job4j.io;

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> phrases = readPhrases();
        int stopIndex = 0;
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String readLine = scanner.nextLine();
        while (!readLine.equals(OUT)) {
            log.add(readLine);
            log.add(System.lineSeparator());
            if (readLine.equals(STOP) || stopIndex == 1) {
                stopIndex = 1;
                if (readLine.equals(CONTINUE)) {
                    stopIndex = 0;
                }
            } else {
                System.out.println(phrases.get(random.nextInt(phrases.size() - 1)));
            }
            readLine = scanner.nextLine();
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()) {
                phrases.add(reader.readLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter reader = new BufferedWriter(new FileWriter(botAnswers))) {
            for (String line : log) {
                reader.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("C:\\projects\\job4j_design\\src\\data\\RandomPhrases.txt",
                "C:\\projects\\job4j_design\\src\\data\\textLog.txt");
        consoleChat.run();
    }
}
