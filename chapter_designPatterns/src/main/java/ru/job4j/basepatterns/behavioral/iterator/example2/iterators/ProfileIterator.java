package ru.job4j.basepatterns.behavioral.iterator.example2.iterators;

import ru.job4j.basepatterns.behavioral.iterator.example2.profile.Profile;

/**
 * Интерфейс итератора
 */

public interface ProfileIterator {
    boolean hasNext();

    Profile getNext();

    void reset();
}
