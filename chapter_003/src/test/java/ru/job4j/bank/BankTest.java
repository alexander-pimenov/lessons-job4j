package ru.job4j.bank;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * BankTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class BankTest {
    Bank bank = new Bank();
    User user1 = new User("Vlad", "4001666", 23);
    User user2 = new User("Vlad", "4001665", 23);
    TreeMap<User, ArrayList<Account>> expected = new TreeMap<>();

    @Test
    public void addUserThenUser() {
        expected.putIfAbsent(new User("Vlad", "4001666", 23), new ArrayList<>());
        bank.addUser(user1);
        assertThat(bank.getTreemap(), is(expected));
    }

    @Test
    public void removeUserThenNothing() {
        bank.addUser(user1);
        bank.deleteUser(user1);
        assertThat(bank.getTreemap(), is(expected));
    }

    @Test
    public void add2AccountsTo1UserThen2Account() {
        bank.addUser(user1);
        bank.addAccount(user1, new Account(2000.0, "AB001"));
        bank.addAccount(user1, new Account(4000.0, "AB002"));
        List<Account> result = bank.getUserAccounts("4001666");
        assertThat(result.size(), is(2));
    }

    @Test
    public void getAccountByRequisiteFromUserPassport() {
        bank.addUser(user1);
        bank.addAccount(user1, new Account(2000.0, "AB001"));
        bank.addAccount(user1, new Account(4000.0, "AB002"));
        Account result = bank.getAccountByRequisiteFromUserPassport("4001666", "AB002");
        assertThat(result.getValues(), is(4000.0));
    }

    @Test
    public void remove1AccountFrom2AccountsThen1Account() {
        bank.addUser(user1);
        bank.addAccount(user1, new Account(2000.0, "AB001"));
        bank.addAccount(user1, new Account(4000.0, "AB002"));
        bank.deleteAccountByRequisites(user1, "AB001");
        assertThat(bank.getTreemap().get(user1).size(), is(1));
    }

    @Test
    public void getUserByPassport() {
        bank.addUser(user1);
        bank.addUser(user2);
        User result = bank.getUserByPassport("4001666");
        assertThat(result, is(user1));
    }

    @Test
    public void transferMoney1() {
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addAccount(user1, new Account(2000.0, "AB001"));
        bank.addAccount(user2, new Account(4000.0, "AB001"));
        bank.transferMoney("4001666", "AB001", "4001665", "AB001", 2000.0);
        double result = bank.getAccountByRequisiteFromUserPassport("4001666", "AB001").getValues();
        assertThat(result, is(0.0));
    }

    @Test
    public void transferMoney2() {
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addAccount(user1, new Account(2000.0, "AB001"));
        bank.addAccount(user2, new Account(4000.0, "AB001"));
        bank.transferMoney("4001666", "AB001", "4001665", "AB001", 2000.0);
        double result = bank.getAccountByRequisiteFromUserPassport("4001665", "AB001").getValues();
        assertThat(result, is(6000.0));
    }
}
