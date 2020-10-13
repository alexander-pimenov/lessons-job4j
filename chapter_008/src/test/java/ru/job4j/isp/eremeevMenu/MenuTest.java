package ru.job4j.isp.eremeevMenu;

import org.junit.Test;

public class MenuTest {

    @Test
    public void whenAddMenuItemsAndCallMethodExecute() {
        Menu menu = new Menu("Задачи");
        Item item1 = new Item("1", "Задача 1");
        Item item11 = new Item("1.1", "Задача 1.1");
        item1.addSubItem(item11);
        menu.addItem(item1);
        menu.showMenu();
        menu.selectItem("1.1");
    }

    @Test
    public void whenAddMenuItemsAndCallMethodExecuteButHaveError() {
        Menu menu = new Menu("Задачи");
        Item item1 = new Item("1", "Задача 1");
        Item item11 = new Item("1.1", "Задача 1.1");
        item1.addSubItem(item11);
        menu.addItem(item1);
        menu.showMenu();
        menu.selectItem("2.2");
    }

    @Test
    public void whenAddMenuItemsThenShowAllItems() {
        Menu menu = new Menu("Задачи");
        Item item1 = new Item("1", "Задача 1");
        Item item11 = new Item("1.1", "Задача 1.1");
        item11.addSubItem(new Item("1.1.1", "Задача 1.1.1"));
        item11.addSubItem(new Item("1.1.2", "Задача 1.1.2"));
        Item item12 = new Item("1.2", "Задача 1.2");
        item1.addSubItem(item11);
        item1.addSubItem(item12);
        Item item2 = new Item("2", "Задача 2");
        Item item22 = new Item("2.2", "Задача 2.2");
        item2.addSubItem(item22);
        menu.addItem(item1);
        menu.addItem(item2);
        menu.showMenu();
    }
}