package ru.job4j.lsp;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * ControlQuality
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ControlQuality {
    private List<Food> newFood;

    private Place warehouse;
    private Place shop;
    private Place trash;

    public ControlQuality(Place warehouse, Place shop, Place trash) {
        this.newFood = new ArrayList<>();
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    public void addNewFood(Food food) {
        this.newFood.add(food);
    }

    private int calculateDateOfUsage(Food food) {
        LocalDate today = LocalDate.now();
        double lifeTimeOfFood = Period.between(food.getCreateDate(), food.getExpireDate()).getDays();
        double howOldFood = Period.between(food.getCreateDate(), today).getDays();
        return (int) (howOldFood / lifeTimeOfFood * 100);
    }

    public void selector() {
        for (Food food : newFood) {
            int usage = this.calculateDateOfUsage(food);
            if (usage >= 25 && usage < 75) {
                shop.distributor(food);
            }
            if (usage >= 75 && usage < 100) {
                food.setDisscount(usage);
                shop.distributor(food);
            }
            if (usage >= 100) {
                trash.distributor(food);
            } else {
                warehouse.distributor(food);
            }
        }
        this.newFood.clear();
    }
}
