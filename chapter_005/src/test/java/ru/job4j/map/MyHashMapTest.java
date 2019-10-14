package ru.job4j.map;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * MyHashMapTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MyHashMapTest {
    private MyHashMap<String, String> myHashMap = new MyHashMap<>();
    private Iterator<MyHashMap.Node> iterator;

    @Before
    public void setUp() {
        myHashMap.insert("1", "value1");
        myHashMap.insert("2", "value2");
        myHashMap.insert("3", "value3");
        myHashMap.insert("4", "value4");
        myHashMap.insert("5", "value5");
        iterator = myHashMap.iterator();
    }

    @Test
    public void shouldSequentiallyReturnItems() {
        MatcherAssert.assertThat(iterator.next(), is(new MyHashMap.Node("1", "value1")));
        MatcherAssert.assertThat(iterator.next(), is(new MyHashMap.Node("2", "value2")));
        MatcherAssert.assertThat(iterator.next(), is(new MyHashMap.Node("3", "value3")));
        MatcherAssert.assertThat(iterator.next(), is(new MyHashMap.Node("4", "value4")));
        MatcherAssert.assertThat(iterator.next(), is(new MyHashMap.Node("5", "value5")));
        //iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnItemsSequentially() {
        MatcherAssert.assertThat(iterator.hasNext(), is(true));
        MatcherAssert.assertThat(iterator.next(), is(new MyHashMap.Node("1", "value1")));
        MatcherAssert.assertThat(iterator.hasNext(), is(true));
        MatcherAssert.assertThat(iterator.next(), is(new MyHashMap.Node("2", "value2")));
        MatcherAssert.assertThat(iterator.hasNext(), is(true));
        MatcherAssert.assertThat(iterator.next(), is(new MyHashMap.Node("3", "value3")));
        MatcherAssert.assertThat(iterator.hasNext(), is(true));
        MatcherAssert.assertThat(iterator.next(), is(new MyHashMap.Node("4", "value4")));
        MatcherAssert.assertThat(iterator.hasNext(), is(true));
        MatcherAssert.assertThat(iterator.next(), is(new MyHashMap.Node("5", "value5")));
        MatcherAssert.assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test
    public void  shouldReturnFalseIfNoAnyItems() {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        iterator = myHashMap.iterator();
        MatcherAssert.assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void  whenGetThenShow() {
        assertThat(myHashMap.get("1"), is("value1"));
    }

    @Test
    public void  whenRemoveThenRemoved() {
        assertThat(myHashMap.amount(), is(5));
        myHashMap.delete("1");
        assertThat(myHashMap.amount(), is(4));
        assertThat(myHashMap.get("1"), nullValue());
    }
}