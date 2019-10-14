package ru.job4j.tracker;

/**
 * StartUITest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }

        @Override
        public String toString() {
            return out.toString();
        }
    };
    private Tracker tracker = new Tracker();
    private Item item1 = this.tracker.add(new Item("test name1", "desc"));
    private Item item2 = this.tracker.add(new Item("test name2", "desc"));
    private Item item3 = this.tracker.add(new Item("test name3", "desc"));

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Input input = new StubInput(Arrays.asList("0", "test name", "desc", "6"));
        new StartUI(input, this.tracker, output).init();
        assertThat(this.tracker.findAll().get(3).getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Input input = new StubInput(Arrays.asList("2", this.item1.getId(), "test replace", "заменили заявку", "6"));
        new StartUI(input, this.tracker, output).init();
        assertThat(this.tracker.findById(this.item1.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenUserDeleteItemThenTrackerHasNothing() {
        Input input = new StubInput(Arrays.asList("3", this.item1.getId(), "3", this.item2.getId(), "3", this.item3.getId(), "6"));
        new StartUI(input, this.tracker, output).init();
        assertThat(this.tracker.findAll().size(), is(0));
    }

    @Test
    public void whenUserWantToShowAllItemsThenItShows() {
        Input input = new StubInput(Arrays.asList("1", "6"));
        new StartUI(input, this.tracker, output).init();
        String expected = String.format("Item with id: %s, name: %s, description: %s%n"
                        + "Item with id: %s, name: %s, description: %s%n"
                        + "Item with id: %s, name: %s, description: %s%n",
                this.item1.getId(), this.item1.getName(), this.item1.getDescription(),
                this.item2.getId(), this.item2.getName(), this.item2.getDescription(),
                this.item3.getId(), this.item3.getName(), this.item3.getDescription());
        assertThat((this.output.toString()), is(expected));
    }

    @Test
    public void whenUserSearchByIdItemsThenItShows() {
        Input input = new StubInput(Arrays.asList("4", this.item1.getId(), "6"));
        new StartUI(input, this.tracker, output).init();
        String expected = String.format("Item with id: %s, name: %s, description: %s%n",
                this.item1.getId(), this.item1.getName(), this.item1.getDescription());
        assertThat((this.output.toString()), is(expected));
    }

    @Test
    public void whenUserSearchByNameItemsThenItShows() {
        Input input = new StubInput(Arrays.asList("5", this.item2.getName(), "6"));
        new StartUI(input, this.tracker, output).init();
        String expected = String.format("Item with id: %s, name: %s, description: %s%n",
                this.item2.getId(), this.item2.getName(), this.item2.getDescription());
        assertThat((this.output.toString()), is(expected));
    }
}