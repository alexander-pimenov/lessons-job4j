package ru.job4j.tracker;

import java.util.List;
import java.util.function.Consumer;

/**
 * StartUI class.
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Get a data from a client.
     */
    private final Input input;
    /**
     * The tracker.
     */
    private final ITracker tracker;
    /**
     * Give a data from a client.
     */
    private final Consumer<String> output;
    /**
     * <p>The constructor.</p>
     * @param input Get data from a client.
     * @param tracker The tracker.
     * @param output
     */
    public StartUI(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * <p>The main loop of the app.</p>
     */
    private boolean working = true;

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, (Tracker) this.tracker, this.output);
        menu.fillActions(this);
        List<Integer> range = menu.getRangeOfMenu();
        do {
            menu.show();
            menu.select(this.input.ask("select:", range));
        } while (this.working);
    }
    public void stop() {
        this.working = false;
    }

    /**
     * <p>The beginning of the app.</p>.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(
                new ConsoleInput()
        ), new Tracker(), System.out::println).init();
    }
}