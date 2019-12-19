package ru.job4j.basepatterns.behavioral.iterator.example2.social_networks;

import ru.job4j.basepatterns.behavioral.iterator.example2.iterators.FacebookIterator;
import ru.job4j.basepatterns.behavioral.iterator.example2.iterators.ProfileIterator;
import ru.job4j.basepatterns.behavioral.iterator.example2.profile.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Социальная сеть Facebook
 */

public class Facebook implements SocialNetwork {
    private List<Profile> profiles;

    public Facebook(List<Profile> cache) {
        if (cache != null) {
            this.profiles = cache;
        } else {
            this.profiles = new ArrayList<>();
        }
    }

    public Profile requestProfileFromFacebook(String profileEmail) {
        // Здесь бы был POST запрос к одному из адресов API Facebook. Но вместо
        // этого мы эмулируем долгое сетевое соединение, прямо как в реальной
        // жизни...
        simulateNetworkLatency();
        System.out.println("Facebook: Loading ' " + profileEmail + " ' over the network...");
        // ...и возвращаем тестовые данные.
        return findProfile(profileEmail);
    }

    public List<String> requestProfileFriendsFromFacebook(String profileEmail, String contactType) {
        // Здесь бы был POST запрос к одному из адресов API Facebook. Но вместо
        // этого мы эмулируем долгое сетевое соединение, прямо как в реальной
        // жизни...
        simulateNetworkLatency();
        System.out.println("Facebook: Loading '" + contactType + "' list of '" + profileEmail + "' over the network...");

        // ...и возвращаем тестовые данные.
        Profile profile = findProfile(profileEmail);
        if (profile != null) {
            return profile.getContacts(contactType);
        }
        return null;
    }

    private Profile findProfile(String profileEmail) {
        for (Profile profile : profiles) {
            if (profile.getEmail().equals(profileEmail)) {
                return profile;
            }
        }
        return null;
    }

    private void simulateNetworkLatency() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public ProfileIterator createFriendsIterator(String profileEmail) {
        return new FacebookIterator(this, "coworkers", profileEmail);
    }

    @Override
    public ProfileIterator createCoworkersIterator(String profileEmail) {
        return new FacebookIterator(this, "coworkers", profileEmail);
    }
}
