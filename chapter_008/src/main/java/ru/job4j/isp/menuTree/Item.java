package ru.job4j.isp.menuTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализующий элемент меню.
 * Для описания пунктов меню.
 */
public class Item implements Action {

    //имя, будем считать, уникально
    private String name;
    //Действия при выборе данного пункта
    private Action action;
    //Дочерние элементы
    private List<Item> children = new ArrayList<>();


    public Item(String name) {
        this.name = name;
    }

    public Item(String name, List<Item> children) {
        this.name = name;
        this.children = children;
    }

    public Item(String name, List<Item> children, Action action) {
        this.name = name;
        this.children = children;
        this.action = action;
    }

    @Override
    public void act() {
        System.out.printf("%s - %s", name, "do some action");
    }

    public Action getAction() {
        return action;
    }

    public List<Item> getChildren() {
        return children;
    }

    public String getName() {
        return this.name;
    }

    public void add(List<Item> list) {
        if (children != null) {
            children.addAll(list);
        } else {
            children = list;
        }
    }

    public List<Item> show() {
        List<Item> list = null;
        System.out.println(name);
        if (children != null) {
            list = children;
        }
        return list;
    }
}
