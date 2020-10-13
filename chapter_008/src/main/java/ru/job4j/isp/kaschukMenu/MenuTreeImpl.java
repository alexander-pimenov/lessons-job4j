package ru.job4j.isp.kaschukMenu;

import java.util.HashMap;
import java.util.Optional;

/**
 * https://github.com/c0dered273/job4j_design/blob/7763c88ece82fa487057436df2c2078f19823af6/chapter_005/src/main/java/ru/job4j/isp/MenuTreeImpl.java
 */

public class MenuTreeImpl implements MenuTree {
    private final MenuItem root;
    private final HashMap<String, MenuItem> locale = new HashMap<>();

    public MenuTreeImpl(String name) {
        this.root = new MenuItemImpl(name, null);
    }


    @Override
    public MenuItem getRoot() {
        return root;
    }

    @Override
    public boolean add(MenuItem parent, MenuItem child) {
        boolean rsl = false;
        if (child == null) {
            return false;
        } else if (parent == null) {
            rsl = root.getItems().add(child);
        } else if (locale.containsKey(parent.getName())) {
            rsl = parent.getItems().add(child);
        }
        locale.put(child.getName(), child);
        return rsl;
    }

    @Override
    public boolean delete(MenuItem parent, MenuItem child) {
        boolean rsl = false;
        if (child == null) {
            return false;
        } else if (parent == null) {
            rsl = root.getItems().remove(child);
        } else if (locale.containsKey(parent.getName())) {
            rsl = parent.getItems().remove(child);
        }
        locale.remove(child.getName());
        return rsl;
    }

    @Override
    public Optional<MenuItem> findByName(String name) {
        return Optional.of(locale.get(name));
    }

    public void print(String prefix){
        for (MenuItem item : root.getItems()){
            item.print(prefix);
        }
    }
}
