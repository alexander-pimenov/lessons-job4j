package ru.job4j.loop;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * CounterTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CounterTest {
    @Test
    public void whenFromZeroToTenThenThirty() {
        Counter sum = new Counter();
        int result = sum.add(0, 10);
        assertThat(result, is(30));
    }
}