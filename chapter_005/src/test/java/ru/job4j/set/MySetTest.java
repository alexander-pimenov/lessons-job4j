package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.list.MyList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * MySetTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MySetTest {
    private MySet<String> mySet = new MySet<>();
    private Iterator<String> iterator;

    @Before
    public void setUp() {
        mySet.add("1");
        mySet.add("2");
        mySet.add("3");
        mySet.add("4");
        mySet.add("5");
        iterator = mySet.iterator();
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
        MySet<String> mySet = new MySet<String>();
        iterator = mySet.iterator();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenPutDuplicateTheNothing() {
        mySet.add("1");
        mySet.add("1");
        mySet.add("1");
        mySet.add("1");
        mySet.add("1");
        mySet.add("6");

        MySet<String> expected = new MySet<>();
        expected.add("1");
        expected.add("2");
        expected.add("3");
        expected.add("4");
        expected.add("5");
        expected.add("6");

        assertThat(mySet, is(expected));
    }

}