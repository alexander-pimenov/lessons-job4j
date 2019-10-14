package ru.job4j.bank;

/**
 * Account
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class Account implements Comparable<Account> {

    private double values;
    private String requisites;

    public Account(double values, String requisites) {
        this.values = values;
        this.requisites = requisites;
    }

    public Account(String requisites) {
        this.values = 0;
        this.requisites = requisites;
    }

    public double getValues() {
        return this.values;
    }

    public String getRequisites() {
        return this.requisites;
    }

    public boolean transfer(Account destination, double amount) {
        boolean success = false;
        if (amount > 0 && amount <= this.values && destination != null) {
            success = true;
            this.values -= amount;
            destination.values += amount;
        }
        return success;
    }

    @Override
    public String toString() {
        return "Account{" + "values=" + values + ", requisites=" + requisites + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        return this.requisites.equals(account.requisites);
    }

    @Override
    public int hashCode() {
        return this.requisites.hashCode();
    }

    @Override
    public int compareTo(Account o) {
        return this.requisites.compareTo(o.requisites);
    }
}