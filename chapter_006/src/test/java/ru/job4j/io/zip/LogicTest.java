package ru.job4j.io.zip;

import org.apache.commons.cli.ParseException;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * LogicTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class LogicTest {

    @Test
    @Ignore
    public void when() throws ParseException {
        String log = System.getProperty("java.io.tmpdir") + "/project.zip";
        var parameters = new String[6];
        parameters[0] = "-d";
        parameters[1] = "C:\\projects\\job4j";
        parameters[2] = "-e";
        parameters[3] = ".txt";
        parameters[4] = "-o";
        parameters[5] = "project.zip";
        Args args = new Args(parameters);
        Logic logic = new Logic();
        logic.zipping(args);
        assertThat(new File((log)).exists(), is(true));
    }
}
