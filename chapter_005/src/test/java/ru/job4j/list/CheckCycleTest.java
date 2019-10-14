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
 * CheckCycleTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CheckCycleTest {
    private CheckCycle<Integer> checker = new CheckCycle<>();
    private Node<Integer> first;
    private Node<Integer> second;
    private Node<Integer> third;
    private Node<Integer> fourth;

    @Before
    public void beforeTest() {
        first = new Node<>(1);
        second = new Node<>(2);
        third = new Node<>(3);
        fourth = new Node<>(4);

        first.setNext(second);
        second.setNext(third);
        third.setNext(fourth);
        fourth.setNext(first);
    }

    @Test
    public void whenLastLinkToFirstTenTrue() {
        boolean result = checker.hasCycle(first);
        assertThat(result, is(true));
    }

    @Test
    public void whenSecondLinkToFirstThenTrue() {
        first.setNext(second);
        second.setNext(first);
        third.setNext(fourth);
        fourth.setNext(null);
        boolean result = checker.hasCycle(first);
        assertThat(result, is(true));
    }
}