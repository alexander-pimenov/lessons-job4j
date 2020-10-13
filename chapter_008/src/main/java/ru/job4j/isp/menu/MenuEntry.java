package ru.job4j.isp.menu;

/**
 * Основа меню - это абстрактный класс MenuEntry.
 * У него есть единственное поле title, а также абстрактный метод run.
 * Это позволит нам описать, что должно произойти при выборе этого
 * пункта меню, не заморачиваясь на создании новых классов.
 */
public abstract class MenuEntry {
    private String title;

    public MenuEntry(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract void run();
}
