package ru.job4j.labmda;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * SomeTestsByMe2Test
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SomeTestsByMe2Test {

    @Test
    public void whenGiveStringThenReverseIt() {
        String expected = "DOM";
        SomeTestsByMe2 object = new SomeTestsByMe2();
        String result = object.workWithString((str) -> {
            String resultInner = "";
            for (int i = str.length() - 1; i >= 0; i--) {
                resultInner += str.charAt(i);
            }
            return resultInner;
        }, "MOD");
        assertThat(result, is(expected));
    }

    @Test
    public void whenGiveDownCaseThenUpCase() {
        String expected = "DOM";
        SomeTestsByMe2 object = new SomeTestsByMe2();
        String result = object.workWithString((str) -> {
            return str.toUpperCase();
            }, "dom");
        assertThat(result, is(expected));
    }

    @Test
    public void whenGiveStringWithWhitespacesThenWithOut() {
        String expected = "LosAngelesisonelove!";
        SomeTestsByMe2 object = new SomeTestsByMe2();
        String result = object.workWithString((str) -> {
            String resultInner = "";
            for (int i = 0; i <= str.length() - 1; i++) {
                if (str.charAt(i) != ' ') {
                    resultInner += str.charAt(i);
                }
            }
            return resultInner;
        }, "Los Angeles is one love!");
        assertThat(result, is(expected));
    }
}