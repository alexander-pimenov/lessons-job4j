package ru.job4j.isp.eremeevMenu;

/*https://github.com/alexeremeev/aeremeev/blob/09a2a2e6a999df28502378da5747ebe8f7aff529/chapter_004/src/test/java/ru/job4j/isp/MenuTest.java
 * */
public class App {
    public static void main(String[] args) {
        Item item11 = new Item("1.1", "Task 1.1");
        Item item12 = new Item("1.2", "Task 1.2");
        Item item1 = new Item("1", "Task 1");
        item1.addSubItem(item11);
        item1.addSubItem(item12);
        item11.addSubItem(new Item("1.1.1", "Task 1.1.1"));
        item11.addSubItem(new Item("1.1.2", "Task 1.1.2"));

        Item item2 = new Item("2", "test 2");
        Item item21 = new Item("2.1", "test 2.1");
        Item item22 = new Item("2.2", "test 2.2");
        Item item223 = new Item("2.2.3", "test 2.2.3");
        item2.addSubItem(item21);
        item2.addSubItem(item22);
        item2.addSubItem(new Item("2.3", "test 2.3"));
        item2.addSubItem(new Item("2.4", "test 2.4"));
        item2.addSubItem(new Item("2.4", "test 2.4.6"));
        item2.addSubItem(new Item("2.4", "test 2.4.5"));

        item2.updateItem(new Item("2.1", "testtesttest 2.1"));

        item21.addSubItem(new Item("2.1.1", "test 2.1.1"));
        item22.addSubItem(item223);

        Menu menu = new Menu("Новое меню");
        menu.addItem(item2);
        menu.addItem(item1);
        menu.addItem(item1);
        menu.showMenu();
        menu.selectItem("1.1.1");
        menu.selectItem("3.1.1");

    }
}
