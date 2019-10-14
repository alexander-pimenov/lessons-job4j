package ru.job4j.array;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
/**
 * ArrayDuplicateTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate check = new ArrayDuplicate();
        String[] input = new String[]  {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] result = check.remove(input);
        String[] expect = new String[]  {"Привет", "Супер", "Мир"};
        assertThat(result, arrayContainingInAnyOrder(expect));
    }
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate2() {
        ArrayDuplicate check = new ArrayDuplicate();
        String[] input = new String[]  {"Привет", "Привет", "Привет", "Привет", "Привет"};
        String[] result = check.remove(input);
        String[] expect = new String[]  {"Привет"};
        assertThat(result, is(expect));
    }
}