package ru.job4j.io.zip;

import org.apache.commons.cli.*;

/**
 * Args
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Args {
    private String directory;
    private String exclude;
    private String output;
    private String[] args;

    public Args(String[] args) throws ParseException {
        //this.directory = args[0];
        //this.exclude = args[1];
        //this.output = args[2];
        this.args = args;
        parser();
    }

    public String directory() {
        return directory;
    }

    public String exclude() {
        return exclude;
    }

    public String output() {
        return output;
    }

    private void parser() throws ParseException {
        Options options = new Options();
        options.addOption(new Option("d", "directory", true, "directory"));
        options.addOption(new Option("e", "exclude", true, "exclude"));
        options.addOption(new Option("o", "output", true, "output"));
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = parser.parse(options, this.args);

        if (commandLine.hasOption("d")) {
            String[] arguments = commandLine.getOptionValues("d");
            this.directory = arguments[0];
            System.out.println("We try to directory with: " + arguments[0]);
        }
        if (commandLine.hasOption("e")) {
            String[] arguments = commandLine.getOptionValues("e");
            this.exclude = arguments[0];
            System.out.println("We try to exclude with: " + arguments[0]);
        }
        if (commandLine.hasOption("o")) {
            String[] arguments = commandLine.getOptionValues("o");
            this.output = arguments[0];
            System.out.println("We try to output with: " + arguments[0]);
        }
    }
}
