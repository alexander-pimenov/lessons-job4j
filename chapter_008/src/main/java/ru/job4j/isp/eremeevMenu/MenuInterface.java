package ru.job4j.isp.eremeevMenu;

public interface MenuInterface {
    /**
     * Add item to menu.
     *
     * @param item item.
     */
    void addItem(Item item);

    /**
     * View menu.
     */
    void showMenu();

    /**
     * Select menu item.
     *
     * @param key menu item key.
     */
    void selectItem(String key);
}
