package ru.job4j.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Bank
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class Bank {
    private TreeMap<User, ArrayList<Account>> treemap = new TreeMap<>();

    public TreeMap<User, ArrayList<Account>> getTreemap() {
        return treemap;
    }

    public boolean addUser(User user) {
        boolean res = false;
        if (this.treemap.putIfAbsent(user, new ArrayList<>()) == null) {
            res = true;
        }
        return res;
    }

    public boolean deleteUser(User user) {
        boolean res = false;
        if (this.treemap.remove(user) != null) {
            res = true;
        }
        return res;
    }

    public boolean addAccount(User user, Account account) {
        boolean res = false;
        final ArrayList<Account> accounts = this.treemap.get(user);
        if (accounts != null && !accounts.contains(account)) {
            accounts.add(account);
            res = true;
        }
        return res;
    }

    public boolean deleteAccountByRequisites(User user, String requisites) {
        boolean res = false;
        final ArrayList<Account> accounts = treemap.get(user);
        if (accounts != null) {
            accounts.removeIf(account -> account.getRequisites().equals(requisites));
            res = true;
        }
        return res;
    }

    public User getUserByPassport(String passport) {
        return treemap.keySet().stream().filter(user -> user.getPassport().equals(passport)).
                findAny().orElse(null);
    }

    public List<Account> getUserAccounts(String passport) {
        List<Account> accounts = this.treemap.get(getUserByPassport(passport));
        return accounts;
    }

    public Account getAccountByRequisiteFromUserPassport(String passport, String requisite) {
        List<Account> accounts = getUserAccounts(passport);
        Account result = null;
        if (accounts != null) {
            result = accounts.stream().filter(account -> account.getRequisites()
                    .equals(requisite))
                    .findAny().orElse(null);
        }
        return result;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean result = false;
        Account account1 = getAccountByRequisiteFromUserPassport(srcPassport, srcRequisite);
        Account account2 = getAccountByRequisiteFromUserPassport(destPassport, destRequisite);
        if (account1 != null && account2 != null && amount > 0) {
            account1.transfer(account2, amount);
            result = true;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Bank{" + "accounts=" + treemap + "}";
    }
}