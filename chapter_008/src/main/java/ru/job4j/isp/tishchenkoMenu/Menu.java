package ru.job4j.isp.tishchenkoMenu;

import java.util.Map;
import java.util.TreeMap;

public class Menu implements FullMenu, AssignTask {

    private Map<String, Task> map = new TreeMap<>();

    public Menu(Map<String, Task> map) {
        this.map = map;
        init();
    }

    @Override
    public Task getTask(String key) {
        Task result = null;
        for (String str : map.keySet()) {
            if (key.equals(str)) {
                System.out.println(String.format("%s - %s", str, map.get(str).getName()));
            }
        }
        return result;
    }

    @Override
    public void init() {
        this.map.put("1.", new Task("task1"));
        this.map.put("1.1", new Task("task2"));
        this.map.put("1.1.1", new Task("task3"));
        this.map.put("1.1.2", new Task("task4"));
        this.map.put("2.", new Task("task5"));
        this.map.put("2.1.", new Task("task6"));
        this.map.put("2.1.1", new Task("task7"));
        this.map.put("2.1.2", new Task("task8"));
    }

    @Override
    public void showMenu() {
        for (String key : map.keySet()) {
            getTask(key);
        }

    }
}
