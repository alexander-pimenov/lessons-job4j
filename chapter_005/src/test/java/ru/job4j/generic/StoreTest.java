package ru.job4j.generic;

        import org.junit.Before;
        import org.junit.Test;

        import static org.hamcrest.MatcherAssert.assertThat;
        import static org.hamcrest.Matchers.is;

/**
 * StoreTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class StoreTest {
    private SimpleArray<User> users;
    private SimpleArray<Role> roles;

    private Role role1;
    private Role role2;
    private Role role3;

    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() {
        role1 = new Role("100");
        role2 = new Role("200");
        role3 = new Role("300");

        user1 = new User("001");
        user2 = new User("002");
        user3 = new User("003");

        users = new SimpleArray<>(new User[] {user1, user2, user3});
        roles = new SimpleArray<>(new Role[] {role1, role2, role3});
    }

    @Test
    public void whenDeleteByIdForUsersThen() {
        UserStore us = new UserStore(users);
        us.delete("001");
        SimpleArray<User> expectedUsers = new SimpleArray<>(new User[] {
                new User("002"),
                new User("003"),
                null});
        UserStore expected = new UserStore(expectedUsers);
        assertThat(us, is(expected));
    }

    @Test
    public void whenDeleteByIdForRolesThen() {
        RoleStore us = new RoleStore(roles);
        us.delete("100");
        SimpleArray<Role> expectedRoles = new SimpleArray<>(new Role[] {
                new Role("200"),
                new Role("300"),
                null});
        RoleStore expected = new RoleStore(expectedRoles);
        assertThat(us, is(expected));
    }

    @Test
    public void whenDeleteByIdThenAddAndEditByReplaceThat() {
        UserStore us = new UserStore(users);
        us.delete("001");
        us.add(new User("new"));
        us.replace("new", new User("newer"));
        SimpleArray<User> expectedUsers = new SimpleArray<>(new User[] {
                new User("002"),
                new User("003"),
                new User("newer")});
        UserStore expected = new UserStore(expectedUsers);
        assertThat(us, is(expected));
    }

    @Test
    public void whenFindByIdThen() {
        UserStore us = new UserStore(users);
        User expected = new User("001");
        assertThat(us.findById("001"), is(expected));
    }
}
