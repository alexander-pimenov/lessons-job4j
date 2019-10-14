package ru.job4j.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * ConvertTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ConvertTest {

    @Test
    public void convert() {

        Integer[][] matrix = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};
        Convert convert = new Convert();
        List<Integer> result = convert.convert(matrix);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertThat(result, is(expected));
    }
}