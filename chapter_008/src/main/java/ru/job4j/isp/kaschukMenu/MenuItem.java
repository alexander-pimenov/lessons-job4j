package ru.job4j.isp.kaschukMenu;

import java.util.List;

public interface MenuItem extends Runnable {
    String getName();
    List<MenuItem> getItems();

    void print(String format);
}
