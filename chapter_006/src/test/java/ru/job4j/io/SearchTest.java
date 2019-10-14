package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * SearchTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SearchTest {

    @Test
    public void whenSomething() throws IOException {
        Search search = new Search();

        String dir = System.getProperty("java.io.tmpdir");
        File parentDir = new File(dir + "/Main");
        parentDir.mkdir();
        File subDir = new File(parentDir.getPath() + "/Subdir");
        subDir.mkdir();
        File firstFile = new File(parentDir, "firstFile.doc");
        File secondFile = new File(parentDir, "secondFile.txt");
        File thirdFile = new File(parentDir, "thirdFile.txt");
        File subDirFile = new File(subDir, "subDirFile.doc");
        firstFile.createNewFile();
        secondFile.createNewFile();
        thirdFile.createNewFile();
        subDirFile.createNewFile();

        List<String> ext = Arrays.asList("doc");
        List<File> expected = Arrays.asList(firstFile, subDirFile);
        List<File> result = search.filter(dir, ext);
        assertThat(result.toString(), is(expected.toString()));
    }
}