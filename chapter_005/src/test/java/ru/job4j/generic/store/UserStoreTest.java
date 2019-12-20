package ru.job4j.generic.store;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;


public class UserStoreTest {

    @Test
    public void whenAddUserThanShouldBeSame() {

        UserStore userStore = new UserStore(5);
        User user = new User("11");

        userStore.add(user);

        assertThat(userStore.findById("11"), is(user));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddUsersBiggerThanSize() {

        UserStore userStore = new UserStore(2);
        User user1 = new User("11");
        User user2 = new User("22");
        User user3 = new User("33");

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
    }

    @Test
    public void whenAddTreeUsersAdnDeleteTwoShouldLeftOne() {

        UserStore userStore = new UserStore(3);
        User user1 = new User("11");
        User user2 = new User("22");
        User user3 = new User("33");

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);

        userStore.delete("11");
        userStore.delete("22");

        assertThat(userStore.findById("33"), is(user3));
    }

    @Test
    public void whenReplaceByIdThanReplaced() {
        UserStore userStore = new UserStore(2);

        User user1 = new User("11");
        User user2 = new User("22");

        userStore.add(user1);
        userStore.add(user2);

        User user3 = new User("555");
        boolean result = userStore.replace("11", user3);

        assertThat(result, is(true));
        assertThat(userStore.findById("555"), is(user3));
    }

    @Test
    public void whenDeleteUserWhitWrongId() {

        UserStore userStore = new UserStore(2);
        User user1 = new User("11");
        User user2 = new User("22");

        userStore.add(user1);
        userStore.add(user2);

        assertFalse(userStore.delete("33"));
    }

    @Test
    public void whenDeleteEmptyArray() {

        UserStore userStore = new UserStore(0);

        assertFalse(userStore.delete("33"));
    }
}