package ru.job4j.isp.eremeevMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item implements Action, Information {
    /**
     * Item key.
     */
    private String keyName;
    /**
     * Item description.
     */
    private String description;
    /**
     * Sub items list.
     */
    private List<Item> subItems;

    /**
     * Constructor.
     *
     * @param key         item key.
     * @param description item description.
     */
    public Item(String key, String description) {
        this.keyName = key;
        this.description = description;
        this.subItems = new ArrayList<>();
    }

    /**
     * Add sub item to item.
     *
     * @param item sub item.
     */
    public void addSubItem(Item item) {
        if (!subItems.contains(item)) {
            this.subItems.add(item);
        }
    }

    /**
     * Update item
     * @param item sub item or root item
     * @return true or false
     */
    public boolean updateItem(Item item) {
        boolean result = false;
        for (Item element : subItems) {
            if (element.getKeyName().equals(item.getKeyName())) {
                element.setDescription(item.getDescription());
                result = true;
            }
        }
        return result;
    }

    /**
     * Key getter.
     *
     * @return key.
     */
    public String getKeyName() {
        return keyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean execute(String key) {
        boolean result = false;
        if (this.keyName.equals(key)) {
            result = true;
            System.out.println("Item " + this.getKeyName() + " do something");
        } else if (!this.subItems.isEmpty()) {
            for (Item subItem : subItems) {
                result = subItem.execute(key);
                if (result) {
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public String itemInfo() {
        StringBuilder builder = new StringBuilder();
        int spacerLength = this.getKeyName().length();
        for (int index = 0; index != spacerLength; index++) {
            builder.append("-");
        }
//        builder.append(String.format("%s %s\n", this.keyName, this.description));
        builder.append(String.format("%s %s%s", this.keyName, this.description, System.getProperty("line.separator")));
        if (!this.subItems.isEmpty()) {
            for (Item subItem : subItems) {
                builder.append(subItem.itemInfo());
            }
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(keyName, item.keyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyName, description, subItems);
    }
}
