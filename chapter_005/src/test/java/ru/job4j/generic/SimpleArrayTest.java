package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * SimpleArrayTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayTest {
    private SimpleArray<String> sa;
    private Iterator<String> iterator;

    @Before
    public void setUp() {
        sa = new SimpleArray<>(new String[] {"1", "2", "3", "4", "5"});
        iterator = sa.iterator();
    }

    @Test
    public void shouldSequentiallyReturnItems() {
        assertThat(iterator.next(), is("1"));
        assertThat(iterator.next(), is("2"));
        assertThat(iterator.next(), is("3"));
        assertThat(iterator.next(), is("4"));
        assertThat(iterator.next(), is("5"));
        //iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnItemsSequentially() {
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("1"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("2"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("3"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("4"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("5"));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test
    public void  shouldReturnFalseIfNoAnyItems() {
        sa = new SimpleArray<>(new String[]{});
        iterator = sa.iterator();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void  whenRemoveAndAddThen() {
        sa.remove(0);
        sa.add("new");
        SimpleArray expected = new SimpleArray<>(new String[] {"2", "3", "4", "5", "new"});
        assertThat(sa, is(expected));
    }

    @Test
    public void  whenSetThen() {
        sa.remove(0);
        sa.add("new");
        sa.set(4, "newer");
        SimpleArray expected = new SimpleArray<>(new String[] {"2", "3", "4", "5", "newer"});
        assertThat(sa, is(expected));
    }

    @Test
    public void  whenGetThenShow() {
        assertThat(sa.get(0), is("1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTryToChangeThenException() {
        sa.set(4, "newer");
    }
}