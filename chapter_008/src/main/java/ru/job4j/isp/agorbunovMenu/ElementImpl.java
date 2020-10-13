package ru.job4j.isp.agorbunovMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ElementImpl implements Element {
    /**
     * Name of element.
     */
    private String name;
    /**
     * Child elements.
     */
    private List<Element> elements = new ArrayList<>();

    /**
     * Main constructor.
     *
     * @param name - name of the element.
     */
    public ElementImpl(String name) {
        this.name = name;
    }

    /**
     * Menu action.
     */
    @Override
    public void execute() {
        System.out.printf("%s - %s", name, "Do some action");
    }

    /**
     * Get list of child elements.
     *
     * @return - child elements.
     */
    @Override
    public List<Element> getElements() {
        return this.elements;
    }


    public void addSubElement(Element childElement) {
        if (!elements.contains(childElement)) {
            this.elements.add(childElement);
        }
    }

    /**
     * Get element name.
     *
     * @return - element name.
     */
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementImpl element = (ElementImpl) o;
        return Objects.equals(name, element.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                '}';
    }
}
