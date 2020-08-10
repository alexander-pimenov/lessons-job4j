package ru.job4j.pimalex78.tracker.start;

import ru.job4j.pimalex78.tracker.models.Item;

import java.util.List;
import java.util.function.Consumer;

public class FindItemsByName extends BaseAction {
    protected FindItemsByName(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, MemTracker tracker, Consumer<String> output) {

        System.out.println("------------ Find task by Name --------------");
        String name = input.ask("Please, enter the item's name that you want to find :");
        List<Item> items = tracker.findByName(name);
        if (items.size() > 0) {
            System.out.println("------------ Your items ------------");
            for (Item item : items) {
                //System.out.println(String.format("Id: %s Name: %s Description: %s",
                output.accept(String.format("Id: %s Name: %s Description: %s",
                        item.getId(), item.getName(), item.getDesc()));
            }
        } else {
            System.out.println(String.format("There is no items with this name %s.", name));
        }
        System.out.println("------------- End of search ---------------");
    }
}
