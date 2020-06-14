package ru.job4j.map.pimalex.simple_hashmap;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    private SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<>();


    @Before
    public void setUp() {
        simpleHashMap.insert("1", "value1");
        simpleHashMap.insert("2", "value2");
        simpleHashMap.insert("3", "value3");
        simpleHashMap.insert("4", "value4");
        simpleHashMap.insert("5", "value5");
    }

    @Test
    public void whenCheckSizeTableAndAmountElements() {
        assertThat(simpleHashMap.size(), is(5));

    }

    @Test
    public void whenInsertSameKeyThenShouldFalseAndOldSize() {
        assertThat(simpleHashMap.size(), is(5));
        assertFalse(simpleHashMap.insert("1", "newValue1"));
        assertThat(simpleHashMap.get("1"), is("value1"));
        assertFalse(simpleHashMap.insert("2", "newValue2"));
        assertThat(simpleHashMap.size(), is(5));
    }

    @Test
    public void whenGetNonExistentKeyThenNull() {
        assertThat(simpleHashMap.get("3"), is("value3"));
        assertNull(simpleHashMap.get("6"));
    }

    @Test
    public void whenRemoveExistentElementThenRemovedOrFalse() {
        assertThat(simpleHashMap.size(), is(5));
        simpleHashMap.delete("2");
        assertThat(simpleHashMap.size(), is(4));
        assertThat(simpleHashMap.get("2"), nullValue());
        assertFalse(simpleHashMap.delete("qqq"));
    }

    @Test
    public void whenCallIterator() {
        Iterator<SimpleHashMap.Node<String, String>> iterator = simpleHashMap.iterator();
        System.out.println(simpleHashMap.size());
        System.out.println(simpleHashMap);
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
    }
}