package ru.job4j.io.exam;

import org.apache.commons.cli.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * LogicTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class LogicTest {
    private static final String LS = System.getProperty("line.separator");
    private File dirForSearch;
    private String pathToDirForSearch;

    @Before
    public void createFilesForTesting() throws IOException {
        File dirTest = new File(System.getProperty("java.io.tmpdir") + "/dirForSearch");
        dirTest.mkdir();
        File firstDir = new File(dirTest, "firstDir");
        File secondDir = new File(firstDir, "secondDir");
        File rootFile = new File(dirTest, "eReadme.txt");
        firstDir.mkdir();
        secondDir.mkdir();
        rootFile.createNewFile();
        new File(firstDir, "fReadme.txt").createNewFile();
        new File(secondDir, "reedme.txt").createNewFile();
        this.dirForSearch = new File(dirTest.getAbsolutePath());
        this.pathToDirForSearch = this.dirForSearch.getAbsolutePath();
    }

    @Test
    @Ignore
    public void whenSearch() throws ParseException, IOException {
        String pathToTemp = System.getProperty("java.io.tmpdir");

        StringBuilder expected = new StringBuilder();
        expected.append(pathToTemp);
        expected.append("dirForSearch");
        expected.append("\\eReadme.txt");
        expected.append(LS);
        expected.append(pathToTemp);
        expected.append("dirForSearch");
        expected.append("\\firstDir\\fReadme.txt");
        expected.append(LS);

        File logFile = new File(pathToTemp + "/SearchLog", "log.txt");
        createFilesForTesting();
        String[] args = {"-d", this.pathToDirForSearch, "-n", ".*readme.txt", "-r", "-o", "log.txt"};
        Args params = new Args(args);
        Logic logic = new Logic();
        logic.search(params);

        FileReader fileReader = new FileReader(logFile);
        char[] buffer = new char[133];
        while (fileReader.ready()) {
            fileReader.read(buffer);
        }
        String result = new String(buffer);
        assertThat(result, is(expected.toString()));
    }

}