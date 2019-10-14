package ru.job4j.tracker;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuTracker {

    /**
     * @param хранит ссылку на объект .
     */
    private Input input;

    /**
     * @param хранит ссылку на объект .
     */
    private final Consumer<String> output;

    /**
     * @param хранит ссылку на объект .
     */
    private Tracker tracker;

    /**
     * @param хранит ссылку на массив типа UserAction.
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Method to get limit.
     *
     * @return array of limit.
     */
    public List<Integer> getRangeOfMenu() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < this.actions.size(); i++) {
            result.add(this.actions.get(i).key());
        }
        return result;
    }

    /**
     * <p>Constructor.</p>
     * @param input   instance of the Input.
     * @param tracker instance of the Tracker.
     * @param output  instance of the Output.
     */
    public MenuTracker(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    private class AddItem extends BaseAction {
        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter a new name of the item :");
            String desc = input.ask("Enter a description of the item :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println(item);
        }
    }

    private class ShowItems extends BaseAction {
        public ShowItems(int key, String name) {
            super(key, name);
        }


        @Override
        public void execute(Input input, Tracker tracker) {
            List<Item> itemOut = tracker.findAll();
            for (Item itemIn : itemOut) {
                output.accept(String.format("Item with id: %s, name: %s, description: %s",
                        itemIn.getId(), itemIn.getName(), itemIn.getDescription()));
            }
        }
    }

    private class EditItem extends BaseAction {
        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter id of the item :");
            Item previous = tracker.findById(id);
            if (previous == null) {
                output.accept("The id is not exist!");
            } else {
                System.out.println(previous);
                String name = input.ask("Enter a new name of the item :");
                String desc = input.ask("Enter a new description of the item :");
                Item next = new Item(name, desc);
                tracker.replace(id, next);
                System.out.println(next);
            }
        }
    }

    private class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter id of the item :");
            if (!tracker.delete(id)) {
                output.accept("The id is not exist!");
            } else {
                output.accept(String.format("------------ The item with id : %s is deleted -----------", id));
            }
        }
    }

    private class FindItemById extends BaseAction {
        public FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter id of the item :");
            Item item = tracker.findById(id);
            if (item == null) {
                output.accept("The id is not exist!");
            } else {
                output.accept(String.format("%s", item));
            }
        }
    }

    private class FindItemsByName extends BaseAction {
        public FindItemsByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter name of the item :");
            List<Item> itemOut = tracker.findByName(name);
            for (Item itemIn : itemOut) {
                output.accept(String.format("Item with id: %s, name: %s, description: %s",
                        itemIn.getId(), itemIn.getName(), itemIn.getDescription()));
            }
        }
    }

    private class ExitProgram extends BaseAction {
        private final StartUI ui;

        public ExitProgram(StartUI ui, int key, String name) {
            super(key, name);
            this.ui = ui;
        }

        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            this.ui.stop();
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Exit Program");
        }
    }

    /**
     * <p>Fill an array.</p>
     * @param ui
     */
    public void fillActions(StartUI ui) {
        this.actions.add(this.new AddItem(0, "Add new Item"));
        this.actions.add(this.new ShowItems(1, "Show all items"));
        this.actions.add(this.new EditItem(2, "Edit item"));
        this.actions.add(this.new DeleteItem(3, "Delete item"));
        this.actions.add(this.new FindItemById(4, "Find item by Id"));
        this.actions.add(this.new FindItemsByName(5, "Find items by name"));
        this.actions.add(this.new ExitProgram(ui, 6, "Exit Program"));
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * <p>Show the menu.</p>
     */
    public void show() {
        System.out.println("Menu.");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }
}