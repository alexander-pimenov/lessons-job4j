package ru.job4j.lsp;

import java.util.List;

/**
 * Place
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Place {
    //List<Food> foodStore;
    boolean distributor(Food food);
    List<Food> showFoodStore();
}
