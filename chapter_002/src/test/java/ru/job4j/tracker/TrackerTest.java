package ru.job4j.tracker;

/**
 * TrackerTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    private Tracker tracker = new Tracker();
    @Test

    public void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
    @Test
    public void whenDeleteElementThenReturnMoreShotArray() {
        Item item1 = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDescription", 123L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.delete(item1.getId());
        List<Item> expect = new ArrayList<>();
        expect.add(item2);
        assertThat(tracker.findAll(), is(expect));
    }
    @Test
    public void whenInputNameThenReturnItemByName() {
        Item item1 = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDescription", 123L);
        Item item3 = new Item("test1", "testDescription", 123L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> expect = new ArrayList<>();
        expect.add(item1);
        expect.add(item3);
        assertThat(tracker.findByName("test1"), is(expect));
    }
}