package ru.job4j.isp.agorbunovMenu;

import java.util.List;

public interface Element {
    /**
     * Menu action.
     */
    void execute();

    /**
     * Child elements.
     * @return list of elements.
     */
    List<Element> getElements();

    /**
     * Get element name.
     * @return - element name.
     */
    String getName();
}
