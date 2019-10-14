package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * //TODO add comments.
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateInputTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.mem));

    }

    @After
    public void loadSys() {
        System.setOut(this.out);
        System.out.println("execute after method");
    }

    @Test
    public void whenInvalidInput1() {
        ValidateInput input = new ValidateInput(
                new StubInput(Arrays.asList("invalid", "1"))
        );
        input.ask("Enter", Arrays.asList(1));
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Please enter validate data again.%n")
                )
        );
    }
    @Test
    public void whenInvalidInput2() {
        ValidateInput input = new ValidateInput(
                new StubInput(Arrays.asList("9", "1"))
        );
        input.ask("Enter", Arrays.asList(1));
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Please select key from menu.%n")
                )
        );
    }
}