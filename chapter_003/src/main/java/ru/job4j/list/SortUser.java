package ru.job4j.list;

import java.util.*;
import java.util.stream.Collectors;

/**
 * SortUser
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class SortUser {
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    public List<User> sortNameLength(List<User> list) {
        return list.stream().sorted(Comparator.comparing(user -> user.getName().length()))
                .collect(Collectors.toList());
    }

    public List<User> sortByAllFields(List<User> list) {
        return list.stream().sorted(Comparator.comparing(User::getName).thenComparing(User::getAge))
                .collect(Collectors.toList());
    }
}
