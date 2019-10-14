package ru.job4j.generic;

import java.util.Iterator;

public class UserStore extends AbstractStore<User> {


    public UserStore(SimpleArray<User> store) {
        super(store);
    }
  /*  public static void main(String[] args) {
        User user1 = new User("001");
        User user2 = new User("002");
        User user3 = new User("003");
        SimpleArray<User> sa = new SimpleArray<>(new User[] {user1, user2, user3});
        UserStore<User> us = new UserStore<>(sa) ;
        us.replace("001", new User("004"));
        us.delete("002");
        us.add(new User("666"));
        us.findById("666");
        System.out.println(us.findById("666"));
        for (int i = 0; i < 10; i++) {
            System.out.println(sa);
        }
    }*/
}
