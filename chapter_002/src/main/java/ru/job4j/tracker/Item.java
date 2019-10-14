package ru.job4j.tracker;

import java.util.List;

/**
 * Item class.
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Item {
    private String id;
    private String name;
    private String description;
    private long created;
    private String comments;

    public Item() {
    }
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Item(String name, String description, long created) {
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public Item(String itemName, String description, String id) {
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }

    public long getCreated() {
        return created;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Item with id: " + id
                + ", name: " + name
                + ", description: " + description;
    }
}
