package ru.job4j.exam;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * AnaliseTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class AnaliseTest {

    Analise analise = new Analise();
    List<Analise.User> oldList = new ArrayList<>();
    List<Analise.User> newList = new ArrayList<>();

    @Before
    public void setUp() {
        oldList.add(new Analise.User(1, "Vlad"));
        oldList.add(new Analise.User(2, "Mike"));
        oldList.add(new Analise.User(3, "King"));
    }

    @Test
    public void whenAddOne() {
        newList.add(new Analise.User(1, "Vlad"));
        newList.add(new Analise.User(2, "Mike"));
        newList.add(new Analise.User(3, "King"));
        newList.add(new Analise.User(4, "New"));
        Analise.Info info = analise.diff(oldList, newList);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getDeleted(), is(0));
        assertThat(info.getChanged(), is(0));
    }

    @Test
    public void whenAddOneAndChangedOne() {
        newList.add(new Analise.User(1, "VladPutin"));
        newList.add(new Analise.User(2, "Mike"));
        newList.add(new Analise.User(3, "King"));
        newList.add(new Analise.User(4, "New"));
        Analise.Info info = analise.diff(oldList, newList);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getDeleted(), is(0));
        assertThat(info.getChanged(), is(1));
    }

    @Test
    public void whenDeletedOne() {
        newList.add(new Analise.User(1, "Vlad"));
        newList.add(new Analise.User(2, "Mike"));
        Analise.Info info = analise.diff(oldList, newList);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getDeleted(), is(1));
        assertThat(info.getChanged(), is(0));
    }

    @Test
    public void whenAddOneAndDeletedOne() {
        newList.add(new Analise.User(1, "Vlad"));
        newList.add(new Analise.User(2, "Mike"));
        newList.add(new Analise.User(4, "New"));
        Analise.Info info = analise.diff(oldList, newList);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getDeleted(), is(1));
        assertThat(info.getChanged(), is(0));
    }

    @Test
    public void whenAddOneAndDeletedTwo() {
        newList.add(new Analise.User(1, "Vlad"));
        newList.add(new Analise.User(4, "New"));
        Analise.Info info = analise.diff(oldList, newList);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getDeleted(), is(2));
        assertThat(info.getChanged(), is(0));
    }

    @Test
    public void whenAddOneAndDeletedOneAndChangedOne() {
        newList.add(new Analise.User(1, "VladPutin"));
        newList.add(new Analise.User(2, "Mike"));
        newList.add(new Analise.User(4, "New"));
        Analise.Info info = analise.diff(oldList, newList);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getDeleted(), is(1));
        assertThat(info.getChanged(), is(1));
    }

    @Test
    public void whenDeletedAll() {
        Analise.Info info = analise.diff(oldList, newList);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getDeleted(), is(3));
        assertThat(info.getChanged(), is(0));
    }
}