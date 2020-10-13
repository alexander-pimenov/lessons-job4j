package ru.job4j.isp.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuDictionary {
    private static final String MENU_PATTERN = "%s - %s\n";
    private List<MenuEntry> entries = new ArrayList<MenuEntry>();
    private boolean isExit = false;
    private boolean allowPurchase = false;
    private boolean allowAdd = false;

    public MenuDictionary(Dictionary dictionary) {

        entries.add(new MenuEntry("Create dictionary with words") {
            @Override
            public void run() {
                System.out.println("Create dictionary with words");
                dictionary.addNewWord("book", "книга");
                dictionary.addNewWord("loved", "любила");
                dictionary.addNewWord("color", "цвет");
                dictionary.addNewWord("blue", "синий");
                dictionary.addNewWord("is", "есть");
                dictionary.addNewWord("queen", "королева");
            }
        });

        entries.add(new MenuEntry("Add new words") {
            @Override
            public void run() {
                System.out.println("Add new words");

                Scanner scanner = new Scanner(System.in);
                System.out.print("Input english word: ");
                String english = scanner.nextLine();
                System.out.print("Input russian word: ");
                String russian = scanner.nextLine();
                if (dictionary.addNewWord(english, russian)) {
                    System.out.println("Word is added successfully");
                }
            }
        });

        entries.add(new MenuEntry("Translate the sentence") {
            @Override
            public void run() {
                System.out.println("Translate the english sentence");
                String sentence = dictionary.readText();
                System.out.println(sentence);
                System.out.println(dictionary.translateSentence(sentence));
            }
        });

        entries.add(new MenuEntry("Exit") {
            @Override
            public void run() {
                isExit = true;
            }
        });
    }

    public void showMenu() {
        while (!isExit) {
            printMenu();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String line = reader.readLine();
                int choice = Integer.parseInt(line);
                if ((choice <= 0) || (choice - 1 >= entries.size())) {
                    System.out.println("ERROR WRONG MENU INDEX: ");
                } else {
                    MenuEntry entry = entries.get(choice - 1);
                    entry.run();
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR NOT a NUMBER: " + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public MenuDictionary addEntry(MenuEntry entry) {
        int index = entries.size() - 1;
        entries.add(index, entry);
        return this;
    }

    private void printMenu() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\nMenu:\n");
        for (int i = 0; i < entries.size(); i++) {
            MenuEntry entry = entries.get(i);
            String entryFormatted = String.format(MENU_PATTERN, (i + 1), entry.getTitle());
            buffer.append(entryFormatted);
        }
        System.out.print(buffer.toString());
    }
}
