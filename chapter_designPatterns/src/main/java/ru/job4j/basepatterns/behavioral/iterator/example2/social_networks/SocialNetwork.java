package ru.job4j.basepatterns.behavioral.iterator.example2.social_networks;

import ru.job4j.basepatterns.behavioral.iterator.example2.iterators.ProfileIterator;

/**
 * Интерфейс социальной сети
 */

public interface SocialNetwork {
    ProfileIterator createFriendsIterator(String profileEmail);

    ProfileIterator createCoworkersIterator(String profileEmail);
}
