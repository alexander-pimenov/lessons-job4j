package ru.job4j.isp.menu;

public class App {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.addEntry(new MenuEntry("test1"){

            @Override
            public void run() {
                System.out.println("test1 run");
            }
        });
        menu.run();

        menu.addEntry(new MenuEntry("test2") {
            @Override
            public void run() {
                System.out.println("test2 run");
            }
        });
    }
}
