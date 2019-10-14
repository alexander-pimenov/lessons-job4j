package ru.job4j.io;

import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CheckNumberTest {

    @Test
    public void whenInputStreamEvenThenTrue() {
        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
        CheckNumber cn = new CheckNumber();
        assertThat(cn.isNumber(in), is(true));
    }

    @Test
    public void whenInputStreamOddThenFalse() {
        ByteArrayInputStream in = new ByteArrayInputStream("3".getBytes());
        CheckNumber cn = new CheckNumber();
        assertThat(cn.isNumber(in), is(false));
    }

    @Test
    public void whenInputStreamIncorrectThenFalse() {
        ByteArrayInputStream in = new ByteArrayInputStream("3".getBytes());
        CheckNumber cn = new CheckNumber();
        assertThat(cn.isNumber(in), is(false));
    }

    @Test
    public void whenHugeNumber() {
        ByteArrayInputStream in = new ByteArrayInputStream("31234567891234567891234567890".getBytes());
        CheckNumber cn = new CheckNumber();
        assertTrue(cn.isNumber(in));
    }

    @Test
    @Ignore
    public void whenCharInTheMiddleThenFalse() {
        ByteArrayInputStream in = new ByteArrayInputStream("123мамамылараму1234".getBytes());
        CheckNumber cn = new CheckNumber();
        assertThat(cn.isNumber(in), is(false));
    }
}