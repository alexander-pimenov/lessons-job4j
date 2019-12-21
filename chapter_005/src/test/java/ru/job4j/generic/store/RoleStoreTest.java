package ru.job4j.generic.store;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddRoleThanShouldBeSame() {

        RoleStore roleStore = new RoleStore(5);
        Role role = new Role("11");

        roleStore.add(role);

        assertThat(roleStore.findById("11"), is(role));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddRolesBiggerThanSize() {

        RoleStore userStore = new RoleStore(2);
        Role role1 = new Role("11");
        Role role2 = new Role("22");
        Role role3 = new Role("33");

        userStore.add(role1);
        userStore.add(role2);
        userStore.add(role3);
    }

    @Test
    public void whenAddTreeRolesAdnDeleteTwoShouldLeftOne() {

        RoleStore roleStore = new RoleStore(3);
        Role role1 = new Role("11");
        Role role2 = new Role("22");
        Role role3 = new Role("33");

        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);

        roleStore.delete("11");
        roleStore.delete("22");

        assertThat(roleStore.findById("33"), is(role3));
    }

    @Test
    public void whenReplaceRoleByIdThanReplaced() {
        RoleStore roleStore = new RoleStore(2);

        Role role1 = new Role("11");
        Role role2 = new Role("22");

        roleStore.add(role1);
        roleStore.add(role2);

        Role role3 = new Role("555");
        boolean result = roleStore.replace("11", role3);

        assertThat(result, is(true));
        assertThat(roleStore.findById("555"), is(role3));
    }

    @Test
    public void whenDeleteRoleWhitWrongId() {

        RoleStore roleStore = new RoleStore(2);
        Role role1 = new Role("11");
        Role role2 = new Role("22");

        roleStore.add(role1);
        roleStore.add(role2);

        assertFalse(roleStore.delete("33"));
    }

    @Test
    public void whenDeleteRoleInEmptyArray() {

        RoleStore roleStore = new RoleStore(0);

        assertFalse(roleStore.delete("33"));
    }
}