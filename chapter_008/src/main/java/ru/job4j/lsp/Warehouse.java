package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Warehouse
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Warehouse implements Place {
    private List<Food> foodStore;

    public Warehouse() {
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
