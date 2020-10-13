package ru.job4j.isp.frolovMenu;

public class StartSimpleMenu {
    public static void main(String[] args) {
        Item item1 = new SimpleItem("Task 1.");
        Item item11 = new SimpleItem("Task 1.1.");
        Item item12 = new SimpleItem("Task 1.2.");
        Item item111 = new SimpleItem("Task 1.1.1.");
        Item item112 = new SimpleItem("Task 1.1.2.");

        item1.add(item11);
        item1.add(item12);
        item11.add(item111);
        item11.add(item112);

        Menu menu = new SimpleMenu();
        menu.add(item1);

        menu.show();

        System.out.println("======================");

        menu.add(new SimpleItem("Task 2."));
        menu.add(new SimpleItem("Task 3."));
        Item item4 = new SimpleItem("Task 4.");
        menu.add(item4);
        Item item41 = new SimpleItem("Task 4.1.");
        Item item411 = new SimpleItem("Task 4.1.1");
        item4.add(item41);
        item41.add(item411);
        item111.add(new SimpleItem("Task 1.1.1.1."));
        menu.show();

        System.out.println("======================");

        item111.action();
    }
}
