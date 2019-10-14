package ru.job4j.labmda;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * LabmdaTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class LabmdaTest {
    Labmda function = new Labmda();

    @Test
    public void whenLinear() {
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSquare() {
        List<Double> result = function.diapason(5, 8, x -> 2 * (x * x) + 4 * x + 1);
        List<Double> expected = Arrays.asList(71D, 97D, 127D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLn() {
        List<Double> result = function.diapason(5, 8, x -> Math.log(x));
        List<Double> expected = Arrays.asList(1.6094379124341003, 1.791759469228055, 1.9459101490553132);
        assertThat(result, is(expected));
    }
}