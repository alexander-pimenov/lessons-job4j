package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * MyArrayListTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class MyArrayListTest {
    private MyArrayList<String> myArrayList = new MyArrayList<String>();
    private Iterator<String> iterator;

    @Before
    public void setUp() {
        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add("3");
        myArrayList.add("4");
        myArrayList.add("5");
        iterator = myArrayList.iterator();
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
        MyArrayList<String> myArrayList = new MyArrayList<String>();
        iterator = myArrayList.iterator();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void  whenGetThenShow() {
        assertThat(myArrayList.get(0), is("1"));
    }

    @Test
    public void  shouldExtendTheArray() {
        MyArrayList<String> myArrayList = new MyArrayList<String>();
        myArrayList.add("1");
        myArrayList.add("1");
        myArrayList.add("1");
        myArrayList.add("1");
        myArrayList.add("1");
        myArrayList.add("1");
        myArrayList.add("1");
        myArrayList.add("1");
        myArrayList.add("1");
        myArrayList.add("1");
        myArrayList.add("1");
        assertThat(myArrayList.size(), is(11));
    }
}