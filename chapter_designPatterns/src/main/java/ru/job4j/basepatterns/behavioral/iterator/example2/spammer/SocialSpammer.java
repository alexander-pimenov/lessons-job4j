package ru.job4j.basepatterns.behavioral.iterator.example2.spammer;

import ru.job4j.basepatterns.behavioral.iterator.example2.iterators.ProfileIterator;
import ru.job4j.basepatterns.behavioral.iterator.example2.profile.Profile;
import ru.job4j.basepatterns.behavioral.iterator.example2.social_networks.SocialNetwork;

/**
 * Приложение рассылки сообщений
 */
public class SocialSpammer {
    public SocialNetwork network;
    public ProfileIterator iterator;

    public SocialSpammer(SocialNetwork network) {
        this.network = network;
    }

    public void sendSpamToFriends(String profileEmail, String message) {
        System.out.println("\nIterating over friends ...\n"); //Перебирая друзей ...
        iterator = network.createFriendsIterator(profileEmail);
        while (iterator.hasNext()) {
            Profile profile = iterator.getNext();
            sendMessage(profile.getEmail(), message);
        }
    }

    public void sendSpamToCoworkers(String profileEmail, String message) {
        System.out.println("\nIterating over coworkers ...\n"); //Перебирая коллег ...
        iterator = network.createCoworkersIterator(profileEmail);
        while (iterator.hasNext()) {
            Profile profile = iterator.getNext();
            sendMessage(profile.getEmail(), message);
        }
    }

    private void sendMessage(String email, String message) {
        System.out.println("Sent message to: " + email + " '. Message body: ' " + message + " ' ");
    }
}
