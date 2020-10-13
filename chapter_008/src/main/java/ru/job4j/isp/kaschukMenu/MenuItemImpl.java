package ru.job4j.isp.kaschukMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuItemImpl implements MenuItem {
    private final String name;
    private final Runnable action;
    private final List<MenuItem> children = new ArrayList<>();

    public MenuItemImpl(String name, Runnable action) {
        this.name = name;
        this.action = action;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<MenuItem> getItems() {
        return children;
    }

    @Override
    public void run() {
        action.run();
    }

    public void print(String prefix){
        System.out.printf("%s %s.%n",prefix, this.name);
        for (MenuItem item : children){
            item.print(String.format("%s%s", prefix, prefix));
        }
    }
}
