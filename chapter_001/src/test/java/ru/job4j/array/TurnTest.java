package ru.job4j.array;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class TurnTest {
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        Turn turner = new Turn();
        int[] input = new int[] {2, 4, 6, 8};
        int[] result = turner.turn(input);
        int[] expect = new int[] {8, 6, 4, 2};
        assertThat(result, is(expect));
    }
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        Turn turner = new Turn();
        int[] input = new int[] {2, 4, 6, 8, 10};
        int[] result = turner.turn(input);
        int[] expect = new int[] {10, 8, 6, 4, 2};
        assertThat(result, is(expect));
    }
}
