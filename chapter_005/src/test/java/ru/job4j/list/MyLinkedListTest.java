package ru.job4j.list;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * MyLinkedListTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MyLinkedListTest {

    private MyLinkedList<Integer> list;
    private Iterator<Integer> iterator;

    @Before
    public void beforeTest() {
        list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        iterator = list.iterator();
    }

    @Test
    public void shouldSequentiallyReturnItems() {
        MatcherAssert.assertThat(iterator.next(), is(3));
        MatcherAssert.assertThat(iterator.next(), is(2));
        MatcherAssert.assertThat(iterator.next(), is(1));
        //iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnItemsSequentially() {
        MatcherAssert.assertThat(iterator.hasNext(), is(true));
        MatcherAssert.assertThat(iterator.next(), is(3));
        MatcherAssert.assertThat(iterator.hasNext(), is(true));
        MatcherAssert.assertThat(iterator.next(), is(2));
        MatcherAssert.assertThat(iterator.hasNext(), is(true));
        MatcherAssert.assertThat(iterator.next(), is(1));
        MatcherAssert.assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test
    public void  shouldReturnFalseIfNoAnyItems() {
        MyLinkedList<String> list = new MyLinkedList<>();
        Iterator<String> iterator = list.iterator();
        MatcherAssert.assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.size(), is(3));
    }

    @Test
    public void whenRemove() {
        list.removeFirst();
        assertThat(list.size(), is(2));
        assertThat(list.get(0), is(2));
    }
}