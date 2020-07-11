package ru.job4j.io_pimalex.zip;

import org.junit.Test;
import picocli.CommandLine;

import java.io.File;
import java.util.Arrays;

public class TarTest {
    @Test
    public void test() {
        String[] args = {"-c", "--file", "result.tar", "file1.txt", "file2.txt"};
        Tar tar = new Tar();
        new CommandLine(tar).parseArgs(args);

        assert !tar.helpRequested;
        assert tar.create;
        assert tar.archive.equals(new File("result.tar"));
        assert Arrays.equals(tar.files, new File[]{new File("file1.txt"), new File("file2.txt")});
    }
}