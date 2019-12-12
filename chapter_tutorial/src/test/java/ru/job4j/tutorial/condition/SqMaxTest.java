package ru.job4j.tutorial.condition;

import org.junit.Test;

/* org.hamcrest.Matchers.is:
 * Creates a matcher that matches if the examined object matches <b>ALL</b> of the specified matchers.
 * For example:
 * assertThat("myValue", allOf(startsWith("my"), containsString("Val")))*/
//import static org.hamcrest.Matchers.is;
/* org.hamcrest.core.Is.is:
 * Decorates another Matcher, retaining the behaviour but allowing tests
 * to be slightly more expressive.
 * For example:  assertThat(cheese, equalTo(smelly))
 *          vs.  assertThat(cheese, is(equalTo(smelly)))*/
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SqMaxTest {

    @Test
    public void whenFirstBiggerThenAnother(){
        SqMax sqMax = new SqMax();
        int result = sqMax.max(8, 7, 6, 5);
        int expected=8;

        assertThat(result, is(expected));
    }
}