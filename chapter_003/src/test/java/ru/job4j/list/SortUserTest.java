package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * SortUserTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SortUserTest {
    private SortUser sortedTreeSet = new SortUser();
    private List<User> userList = List.of(
            new User("Diablo", 666),
            new User("John", 27),
            new User("I", 32),
            new User("Nil", 37),
            new User("I", 3)
    );
    private List<User> expect = new ArrayList<>();

    @Test
    public void whenSortByAgeThenSortByAge() {
        List<User> expect = List.of(
                new User("I", 3),
                new User("John", 27),
                new User("I", 32),
                new User("Nil", 37),
                new User("Diablo", 666)
        );
        Set<User> result = sortedTreeSet.sort(userList);
        assertThat(result.toString(), is(expect.toString()));
    }

    @Test
    public void whenInArrayListToSortNameForLength() {
        List<User> expect = List.of(
                new User("I", 32),
                new User("I", 3),
                new User("Nil", 37),
                new User("John", 27),
                new User("Diablo", 666)
        );
        List<User> result = sortedTreeSet.sortNameLength(userList);
        assertThat(result.toString(), is(expect.toString()));
    }

    @Test
    public void sortByAllFields() {
        List<User> expect = List.of(
                new User("Diablo", 666),
                new User("I", 3),
                new User("I", 32),
                new User("John", 27),
                new User("Nil", 37)
        );
        List<User> result = sortedTreeSet.sortByAllFields(userList);
        assertThat(result.toString(), is(expect.toString()));
    }
}