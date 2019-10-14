package ru.job4j.tree;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * TreeTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {
    private Tree<String> tree = new Tree<>("1");
    private Iterator<String> iterator;

    @Before
    public void setUp() {
        tree.add("1", "2");
        tree.add("1", "3");
        tree.add("2", "4");
        tree.add("2", "5");
        iterator = tree.iterator();
    }

    @Test
    public void shouldSequentiallyReturnItems() {
        MatcherAssert.assertThat(iterator.next(), is("1"));
        MatcherAssert.assertThat(iterator.next(), is("2"));
        MatcherAssert.assertThat(iterator.next(), is("3"));
        MatcherAssert.assertThat(iterator.next(), is("4"));
        MatcherAssert.assertThat(iterator.next(), is("5"));
        //iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnItemsSequentially() {
        MatcherAssert.assertThat(iterator.hasNext(), is(true));
        MatcherAssert.assertThat(iterator.next(), is("1"));
        MatcherAssert.assertThat(iterator.hasNext(), is(true));
        MatcherAssert.assertThat(iterator.next(), is("2"));
        MatcherAssert.assertThat(iterator.hasNext(), is(true));
        MatcherAssert.assertThat(iterator.next(), is("3"));
        MatcherAssert.assertThat(iterator.hasNext(), is(true));
        MatcherAssert.assertThat(iterator.next(), is("4"));
        MatcherAssert.assertThat(iterator.hasNext(), is(true));
        MatcherAssert.assertThat(iterator.next(), is("5"));
        MatcherAssert.assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7).isPresent(), is(false));
    }

    @Test
    public void whenIsNotBinary() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        assertThat(tree.isBinary(), is(false));
    }

    @Test
    public void whenIsBinary() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        assertThat(tree.isBinary(), is(true));
    }
}