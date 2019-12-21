package ru.job4j.generic.store;

/**
 * Класс-хранилище для объектов User
 */

public class UserStore extends AbstractStore<User> {

    public UserStore(int size) {
        super(size);
    }

}
