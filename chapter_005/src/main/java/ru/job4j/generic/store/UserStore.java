package ru.job4j.generic.store;

/**
 * Класс-хранилище
 */

public class UserStore extends AbstractStore<User> {

    public UserStore(int size) {
        super(size);
    }

}
