package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tracker class.
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Tracker implements ITracker {
    private final List<Item> items = new ArrayList<>();
    private int position = 0;
    private static final Random RN = new Random();
    /**
     * <p>Add a new item to the Tracker.</p>
     *
     * @param item a new item of the Tracker.
     * @return a added item of the Tracker.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(position++, item);
        return item;
    }
    /**
     * <p>Generation new random id.</p>
     *
     * @return a random id.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + this.RN.nextInt(100));
    }
    /**
     * <p>Replace the old item to a new one.</p>
     *
     * @param item a new item.
     * @param id id of the old item.
     */
    public boolean replace(String id, Item item) {
        boolean check = false;
        for (int index = 0; index != this.position; index++) {
            if (items.get(index).getId().equals(id)) {
                item.setId(id);
                items.set(index, item);
                check = true;
                break;
            }
        }
        return check;
    }
    /**
     * <p>Delete a item of the Tracker.</p>
     *
     * @param id id of a element of Tracker.
     */
    public boolean delete(String id) {
        boolean check = false;
        for (int index = 0; index != this.position; index++) {
            if (items.get(index).getId().equals(id)) {
                this.items.remove(index);
                //System.arraycopy(this.items, index + 1, this.items, index, this.items.size() - 1 - index);
                position--;
                check = true;
                break;
            }
        }
        return check;
    }
    /**
     * <p>Find all the elemets of the Tracker.</p>
     *
     * @return array of elements of the Tracker.
     */
    public List<Item> findAll() {
        return this.items;
    }
    /**
     * <p>Find by name a item of the Tracker.</p>
     *
     * @param key a name of a item of the Tracker.
     * @return array of items with the name.
     */
    public List<Item> findByName(String key) {
        int i = 0;
        List<Item> result = new ArrayList<>();
        for (int index = 0; index != this.position; index++) {
            if (this.items.get(index).getName().equals(key)) {
                result.add(i, this.items.get(index));
                i++;
            }
        }
        return result;
        //return Arrays.copyOf(result, i);
    }
    /**
     * <p>Find by id a item of the Tracker.</p>
     *
     * @param id of a item of the Tracker.
     * @return a item with the id.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}