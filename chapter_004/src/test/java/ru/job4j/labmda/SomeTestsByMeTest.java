package ru.job4j.labmda;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * SomeTestsByMeTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SomeTestsByMeTest {
    @Test
    public void whenGiveStringThenReverseIt() {
        String expected = "DOM";
        SomeTestsByMe object = new SomeTestsByMe();
        String result = object.reverse.stringFun("MOD");
        assertThat(result, is(expected));
    }

    @Test
    public void whenGiveDownCaseThenUpCase() {
        String expected = "DOM";
        SomeTestsByMe object = new SomeTestsByMe();
        String result = object.upCase.stringFun("dom");
        assertThat(result, is(expected));
    }

    @Test
    public void whenGiveStringWithWhitespacesThenWithOut() {
        String expected = "LosAngelesisonelove!";
        SomeTestsByMe object = new SomeTestsByMe();
        String result = object.removeWhitespaces.stringFun("Los Angeles is one love!");
        assertThat(result, is(expected));
    }
}
