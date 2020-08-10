package ru.job4j.pimalex78.tracker.start;

import ru.job4j.pimalex78.tracker.models.Item;

import java.util.function.Consumer;

public class ShowItems extends BaseAction {
    protected ShowItems(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, MemTracker tracker, Consumer<String> output) {

        System.out.println("-------------- Show all items --------------");
        for (Item item : tracker.findAll()) { //(Item item : items)
            output.accept(String.format("Id: %s Name: %s Description: %s",
                    //System.out.println(String.format("Id: %s Name: %s Description: %s",
                    item.getId(), item.getName(), item.getDesc()));
        }
        System.out.println("--------------- End of list ---------------");
    }
}
