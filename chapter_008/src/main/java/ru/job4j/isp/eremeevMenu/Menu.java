package ru.job4j.isp.eremeevMenu;

import java.util.Map;
import java.util.TreeMap;

public class Menu implements MenuInterface {
    /**
     * Name our menu.
     */
    private String nameMenu;
    /**
     * Item map.
     */
    private Map<String, Item> items;
//    private Map<String, Item> items = new TreeMap<>();

    public Menu() {
        this.items = new TreeMap<>();
    }

    public Menu(String nameMenu) {
        this.nameMenu = nameMenu;
        this.items = new TreeMap<>();
    }

    @Override
    public void addItem(Item item) {
        if (!items.containsKey(item.getKeyName())) {
            this.items.put(item.getKeyName(), item);
        }
    }

    @Override
    public void showMenu() {
        System.out.println(nameMenu); //красивый вывод ("-= " + nameMenu + "=-")
        for (String strKey : items.keySet()) {
            System.out.print(items.get(strKey).itemInfo());
        }
    }


    @Override
    public void selectItem(String key) {
        String subKey;
        if (key.length() > 1) {
            subKey = key.substring(0, 1);
        } else {
            subKey = key;
        }
        boolean result = false;
        if (this.items.containsKey(subKey)) {
            result = this.items.get(subKey).execute(key);
        }
        if (!result) {
            System.out.println("Error, no such item in this menu");
        }

    }
}
