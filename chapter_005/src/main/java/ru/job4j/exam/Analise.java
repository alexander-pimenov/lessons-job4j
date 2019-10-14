package ru.job4j.exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Analise
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Analise {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, User> currentMap = new HashMap<>();
        for (User user : current) {
            currentMap.put(user.id, user);
        }
       for (User user : previous) {
           if (!currentMap.containsKey(user.id)) {
               info.deleted++;
           }
           User newUser = currentMap.get(user.id);
           if (newUser != null && !newUser.equals(user)) {
               info.changed++;
           }
           info.added = current.size() - previous.size() + info.deleted;
       }
        return info;
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {

        private int added;
        private int changed;
        private int deleted;

        public Info() {
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }

}