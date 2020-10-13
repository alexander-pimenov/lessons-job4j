package ru.job4j.isp.kaschukMenu;

public interface MenuTreeEdit {
    boolean add(MenuItem parent, MenuItem child);

    boolean delete(MenuItem parent, MenuItem child);
}
