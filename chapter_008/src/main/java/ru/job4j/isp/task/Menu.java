package ru.job4j.isp.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static final String MENU_PATTERN = "%s - %s\n";
    private List<MenuEntry> entries = new ArrayList<MenuEntry>();
    private boolean isExit = false;
    private boolean allowPurchase = false;
    private boolean allowAdd = false;

    public Menu(Points points, double a, double b, double c) {

        entries.add(new MenuEntry("Create points") {
            @Override
            public void run() {
                System.out.println("Create points");
                points.add(new Point(1, 1));
                points.add(new Point(2, 3));
                points.add(new Point(3, 3));
            }
        });

        entries.add(new MenuEntry("Print all points") {
            @Override
            public void run() {
                System.out.println("Print all points");
                points.printAllPoints();
            }
        });

        entries.add(new MenuEntry("Find the farther point") {
            @Override
            public void run() {
                System.out.println("The farther point is ");
                System.out.println(points.fartherPoint(a, b, c));
            }
        });

        entries.add(new MenuEntry("Find the immediate point") {
            @Override
            public void run() {
                System.out.println("The immediate point is ");
                System.out.println(points.immediatePoint(a, b, c));
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

    public Menu addEntry(MenuEntry entry) {
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