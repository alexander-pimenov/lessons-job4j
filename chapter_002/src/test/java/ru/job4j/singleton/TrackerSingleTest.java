package ru.job4j.singleton;

/**
 * TrackerSingleTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class TrackerSingleTest {
    @Test
    public void whenCreateFirstAndSecondBothHoveToBeEqual1() {
        TrackerSingle1 tracker1 =  TrackerSingle1.INSTANCE;
        TrackerSingle1 tracker2 =  TrackerSingle1.INSTANCE;
        assertThat(tracker1, is(tracker2));
    }

    @Test
    public void whenCreateFirstAndSecondBothHoveToBeEqual2() {
        TrackerSingle2 tracker1 =  TrackerSingle2.getInstance();
        TrackerSingle2 tracker2 =  TrackerSingle2.getInstance();
        assertThat(tracker1, is(tracker2));
    }

    @Test
    public void whenCreateFirstAndSecondBothHoveToBeEqual3() {
        TrackerSingle3 tracker1 =  TrackerSingle3.getInstance();
        TrackerSingle3 tracker2 =  TrackerSingle3.getInstance();
        assertThat(tracker1, is(tracker2));
    }

    @Test
    public void whenCreateFirstAndSecondBothHoveToBeEqual4() {
        TrackerSingle4 tracker1 =  TrackerSingle4.getInstance();
        TrackerSingle4 tracker2 =  TrackerSingle4.getInstance();
        assertThat(tracker1, is(tracker2));
    }
}