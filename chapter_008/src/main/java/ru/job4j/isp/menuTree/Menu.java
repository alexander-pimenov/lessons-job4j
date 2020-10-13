package ru.job4j.isp.menuTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Меню.
 */
public class Menu {

    private String name;
    private List<Item> itemList = new ArrayList<>();

    public Menu(String name) {
        this.name = name;
    }

    /**
     * Get menu name.
     *
     * @return - menu name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get list of menu elements.
     *
     * @return - menu elements.
     */
    public List<Item> getItemList() {
        return itemList;
    }

    /**
     * Метод добавляет потомка к предку.
     * Служит, чтоб сконтруировать меню.
     *
     * @param parentName имя предка.
     * @param child      потомок.
     */
    void add(String parentName, Item child) {

    }

    public void add(List<Item> items) {
        itemList.addAll(items);
    }

    /**
     * Получает пункт по имени.
     * Уже из него можно вытащить действие,
     * которое можно будет вызвать.
     *
     * @param name имя пункта меню.
     * @return объект пункта меню или null,
     * если такого пункта нет.
     */
    Item get(String name) {
        Item rst = null;
        for (Item i : itemList) {
            String nameItem = i.getName();
            if (nameItem.equals(name)) {
                rst = i;
                break;
            }
        }
        return rst;
    }

    /**
     * Возвращает строковое представление меню.
     *
     * @return строковое представление меню.
     */
    String print() {
        return null;
    }

}
