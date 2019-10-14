package ru.job4j.loop;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * FactorialTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class FactorialTest {
    @Test
    public void when5Then120() {
        Factorial sum = new Factorial();
        int result = sum.calc(5);
        assertThat(result, is(120));
    }
    @Test
    public void when0Then1() {
        Factorial sum = new Factorial();
        int result = sum.calc(0);
        assertThat(result, is(1));
    }
}