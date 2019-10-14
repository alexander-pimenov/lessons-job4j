package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import java.util.HashMap;
import java.util.Map;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * UserTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserTest {
    private User user1 = new User("Mark", 1, new Calendar());
    private User user2 = new User("Mark", 1, new Calendar());
    private User user3 = new User("Mark", 1, new Calendar());


    Map<User, Object> container = new HashMap<>();

    @Before
    public void beforeTest() {
        container.put(user1, "First");
        container.put(user2, "Second");
        container.put(user3, "Third");
    }

    @Test
    @Ignore
    public void print() {
        System.out.println(container);
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(user1.equals(user2));
        System.out.println(user1.equals(user1));
    }

    @Test
    public void shouldBeTheSame() {
        assertThat(user1, is(user1)); //Рефлективность
        assertThat(user2, is(user2)); //Симметричность
        assertThat(user1, is(user2)); //Симметричность
        assertThat(user1, is(user2)); //Непротиворечивость
        assertThat(user2, is(user3)); //Транзитивность
        assertThat(user1, is(user3)); //Транзитивность
        assertThat(user1.equals(null), is(false)); //null == false
        assertThat(user1.hashCode(), is(user2.hashCode()));
    }
}