package ru.job4j.lsp;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * ControlQualityTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ControlQualityTest {

    @Test
    public void whenFoodIsOk() {
        Place warehouse = new Warehouse();
        Place trash = new Trash();
        Place shop = new Shop();

        Food eggs = new Food(
                "eggs",
                LocalDate.of(2019, Month.JUNE, 20),
                LocalDate.of(2019, Month.JUNE, 1),
                30
        );

        ControlQuality controlQuality = new ControlQuality(warehouse, shop, trash);
        controlQuality.addNewFood(eggs);
        controlQuality.selector();

        assertThat(shop.showFoodStore().get(0).getName(), is("eggs"));
        assertTrue(shop.showFoodStore().get(0).getDisscount() > 75);
    }

    @Test
    public void whenFoodIsNotOk() {
        Place warehouse = new Warehouse();
        Place trash = new Trash();
        Place shop = new Shop();

        Food milk = new Food(
                "Milk",
                LocalDate.of(2019, Month.JUNE, 5),
                LocalDate.of(2019, Month.JUNE, 1),
                50
        );

        ControlQuality controlQuality = new ControlQuality(warehouse, shop, trash);
        controlQuality.addNewFood(milk);
        controlQuality.selector();

        assertThat(trash.showFoodStore().get(0).getName(), is("Milk"));
    }
}