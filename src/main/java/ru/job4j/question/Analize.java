package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0, changed = 0, deleted;
        Map<Integer, String> prevMap = new HashMap<>();
        for (User user : previous) {
            prevMap.put(user.getId(), user.getName());
        }

        for (User user : current) {
            String prevName = prevMap.remove(user.getId());
            if (prevName == null) {
                added++;
            } else if (!prevName.equals(user.getName())) {
                changed++;
            }
        }

        deleted = prevMap.size();

        return new Info(added, changed, deleted);
    }
}

