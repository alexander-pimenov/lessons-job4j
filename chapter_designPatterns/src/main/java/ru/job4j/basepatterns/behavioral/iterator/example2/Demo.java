package ru.job4j.basepatterns.behavioral.iterator.example2;

import ru.job4j.basepatterns.behavioral.iterator.example2.profile.Profile;
import ru.job4j.basepatterns.behavioral.iterator.example2.social_networks.Facebook;
import ru.job4j.basepatterns.behavioral.iterator.example2.social_networks.LinkedIn;
import ru.job4j.basepatterns.behavioral.iterator.example2.social_networks.SocialNetwork;
import ru.job4j.basepatterns.behavioral.iterator.example2.spammer.SocialSpammer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Перебор профилей социальной сети.
 * <p>
 * Клиентский код.
 * Демо-класс. Здесь всё сводится воедино.
 * <p>
 * В этом примере итератор помогает перебирать профили пользователей социальной сети,
 * не раскрывая клиентскому коду подробности коммуникации с самой сетью.
 */
public class Demo {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Пожалуйста, укажите социальную сеть для таргетинга на спам (по умолчанию: Facebook)
        System.out.println("Please specify social network to target spam tool (default:Facebook):");
        System.out.println("1. Facebook");
        System.out.println("2. LinkedIn");
        String choice = scanner.nextLine();

        SocialNetwork network;
        if (choice.equals("2")) {
            network = new LinkedIn(createTestProfiles());
        } else {
            network = new Facebook(createTestProfiles());
        }

        SocialSpammer spammer = new SocialSpammer(network);
        spammer.sendSpamToFriends("anna.smith@bing.com",
                "Hey! This is Anna's friend Josh. Can you do me a favor and like this post [link]?");
        spammer.sendSpamToCoworkers("anna.smith@bing.com",
                "Hey! This is Anna's boss Jason. Anna told me you would be interested in [link].");

    }

    private static List<Profile> createTestProfiles() {
        List<Profile> data = new ArrayList<>();
        data.add(new Profile("anna.smith@bing.com", "Anna Smith", "friends:mad_max@ya.com", "friends:catwoman@yahoo.com", "coworkers:sam@amazon.com"));
        data.add(new Profile("mad_max@ya.com", "Maximilian", "friends:anna.smith@bing.com", "coworkers:sam@amazon.com"));
        data.add(new Profile("bill@microsoft.eu", "Billie", "coworkers:avanger@ukr.net"));
        data.add(new Profile("avanger@ukr.net", "John Day", "coworkers:bill@microsoft.eu"));
        data.add(new Profile("sam@amazon.com", "Sam Kitting", "coworkers:anna.smith@bing.com", "coworkers:mad_max@ya.com", "friends:catwoman@yahoo.com"));
        data.add(new Profile("catwoman@yahoo.com", "Liza", "friends:anna.smith@bing.com", "friends:sam@amazon.com"));
        return data;

    }
}
