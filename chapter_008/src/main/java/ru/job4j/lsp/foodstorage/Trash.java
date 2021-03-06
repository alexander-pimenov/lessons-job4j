package ru.job4j.lsp.foodstorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Trash
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Trash implements Place {
    private List<Food> foodStore;

    public Trash() {
        this.foodStore = new ArrayList<>();
    }

    @Override
    public boolean distributor(Food food) {
        return this.foodStore.add(food);
    }

    @Override
    public List<Food> showFoodStore() {
        return this.foodStore;
    }
}
