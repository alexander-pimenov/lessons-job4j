package ru.job4j.isp.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Теперь нужен контейнер,
 * который будет в хранить все пункты меню и выводить
 * их в бесконечном цикле. Этим будет заниматься
 * класс Menu. В качестве дополнения, он будет
 * автоматически создавать пункт меню Exit и
 * добавлять его в конец списка.
 */
public class Menu {
    private static final String MENU_PATTERN = "%s - %s\n";
    private List<MenuEntry> entries = new ArrayList<>();
    private boolean isExit = false;
    private boolean allowPurchase = false;
    private boolean allowAdd = false;

    public Menu() {
        // Добавляем пункт меню Exit
        entries.add(new MenuEntry("Exit") {
            @Override
            public void run() {
                isExit = true;
            }
        });
    }

    public void run() {
        // Бесконечный цикл, пока не нажали кнопку выход
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!isExit) {
            printMenu();
            try {
                String line = reader.readLine();
                int choice = Integer.parseInt(line);
                // Выбираем нажатый пункт меню и выполняем его код
                MenuEntry entry = entries.get(choice - 1);
                entry.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void printMenu() {
        System.out.println(entries);
    }

    public void addEntry(MenuEntry entry) {
        entries.add(entry);
    }
}
