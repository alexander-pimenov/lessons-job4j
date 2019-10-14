package ru.job4j.bank;

import java.util.Objects;

/**
 * User
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class User implements Comparable<User> {
    private String name = null;
    private String passport = null;
    private int age = -1;

    public User(String name, String passport, int age) {
        this.name = name;
        this.passport = passport;
        this.age = age;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(User o) {
        return this.passport.compareTo(o.passport);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        if (this.name != null ? !this.name.equals(user.name) : user.name != null) {
            return false;
        }
        if (this.age != -1 ? !(this.age ==  user.age) : user.age != -1) {
            return false;
        }
        return this.passport != null ? this.passport.equals(user.passport) : user.passport == null;

    }

    @Override
    public String toString() {
        return "User{" + "Name=" + name
                + ", passport=" + passport
                + ", age=" + age + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passport, age);
    }
}