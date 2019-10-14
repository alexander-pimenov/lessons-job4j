package ru.job4j.list;

/**
 * User
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class User implements Comparable<User> {
    private int id;
    private String name;
    private String ciy;
    private int age;

    public User(int id, String name, String ciy) {
        this.id = id;
        this.name = name;
        this.ciy = ciy;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCiy() {
        return ciy;
    }

    public int getAge() {
        return age;
    }


    @Override
    public int compareTo(User o) {
        return Integer.compare(age, o.getAge());
    }

    @Override
    public String toString() {
        return "Name: " + name
                + ", age: " + age;
    }
}
