package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * UserConvertTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class UserConvertTest {
    @Test
    public void when() {
        List<User> userList = new ArrayList<>();
        UserConvert convertList = new UserConvert();
        userList.add(new User(1, "John", "Moscow"));
        userList.add(new User(2, "Petr", "Moscow"));
        userList.add(new User(3, "Mike", "Saint-Peterburg"));
        HashMap<Integer, User> result = convertList.process(userList);

        assertThat(result.get(1).getName(), is("John"));
        assertThat(result.get(2).getName(), is("Petr"));
        assertThat(result.get(3).getName(), is("Mike"));
    }
}