package ru.job4j.isp.agorbunovMenu;

import java.util.*;

public class Menu {
    /**
     * Menu name.
     */
    private String name;
    /**
     * Menu elements.
     */
    private List<Element> elementList = new ArrayList<>();

    /**
     * Main constructor.
     *
     * @param name - menu name.
     */
    public Menu(String name) {
        this.name = name;
    }

    /**
     * Get list of menu elements.
     *
     * @return - menu elements.
     */
    public List<Element> getList() {
        return this.elementList;
    }

    /**
     * Get menu name.
     *
     * @return - menu name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Draw tree of menu elements.
     *
     * @param elements - list of elements.
     * @param i        - level of elements.
     */
    private void drawTree(List<Element> elements, int i) {
        for (Element e : elements) {
            for (int x = 0; x < i; x++) {
                System.out.print("--");
            }
            System.out.println(e.getName());
            if (e.getElements().size() > 0) {
                drawTree(e.getElements(), i + 1);
            }
        }
    }

    /**
     * View all menu elements.
     */
    public void drawMenu() {
        System.out.println(this.getName());
        drawTree(this.elementList, 1);
    }

    public void addSub(String parentName, Element child) {
        //не реализовано

    }

    public Element getItem(String nameItem) {
        String subKey;
        for (Element elm : elementList) {
            if (elm.getName().equals(nameItem)) {
                return elm;
            }
        }
        throw new NoSuchElementException("This item is not in the list.");
    }

}
