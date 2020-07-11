package ru.job4j.io_pimalex.zip;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

public class Tar {
    @Option(names = "-c", description = "create a new archive")
    boolean create;

    @Option(names = {"-f", "--file"}, paramLabel = "ARCHIVE", description = "the archive file")
    File archive;

    @Parameters(paramLabel = "FILE", description = "one ore more files to archive")
    File[] files;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display a help message")
    boolean helpRequested = false;

//    String[] args = {"-c", "--file", "result.tar", "file1.txt", "file2.txt"};

    public static void main(String[] args) {
        args = new String[]{"-c", "--file", "result.tar", "file1.txt", "file2.txt"};
        Tar tar = new Tar();
            CommandLine.ParseResult parseResult = new CommandLine(tar).parseArgs(args);
            parseResult.originalArgs().forEach(System.out::println);
    }
}
