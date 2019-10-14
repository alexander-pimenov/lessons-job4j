package ru.job4j.io.exam;

import org.apache.commons.cli.*;

/**
 * Args
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Args {
    private String directory = null;
    private String name = null;
    private Boolean match = true;
    private Boolean fullMatch = false;
    private Boolean regular = false;
    private String output = null;
    private String[] args;

    public Args(String[] args) throws ParseException {
        this.args = args;
        parser();
    }

    public String directory() {
        return directory;
    }

    public String name() {
        return name;
    }

    public Boolean match() {
        return match;
    }

    public Boolean fullMatch() {
        return fullMatch;
    }

    public Boolean regular() {
        return regular;
    }

    public String output() {
        return output;
    }

    private void parser() throws ParseException {
        Options options = new Options();
        options.addOption(new Option("d", "directory", true, "directory"));
        options.addOption(new Option("n", "name", true, "name"));
        options.addOption(new Option("m", "match", false, "match"));
        options.addOption(new Option("f", "fullMatch", false, "fullMatch"));
        options.addOption(new Option("r", "regular", false, "regular"));
        options.addOption(new Option("o", "output", true, "output"));
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = parser.parse(options, this.args);

        if (commandLine.hasOption("d")) {
            String[] arguments = commandLine.getOptionValues("d");
            this.directory = arguments[0];
            System.out.println("We try directory with: " + arguments[0]);
        }
        if (commandLine.hasOption("n")) {
            String[] arguments = commandLine.getOptionValues("n");
            this.name = arguments[0];
            System.out.println("We try file name with: " + arguments[0]);
        }
        if (commandLine.hasOption("m")) {
            String[] arguments = commandLine.getOptionValues("m");
            this.match = true;
            this.fullMatch = false;
            this.regular = false;
            System.out.println("We try with match");
        }
        if (commandLine.hasOption("f")) {
            String[] arguments = commandLine.getOptionValues("f");
            this.match = false;
            this.fullMatch = true;
            this.regular = false;
            System.out.println("We try with full match");
        }
        if (commandLine.hasOption("r")) {
            String[] arguments = commandLine.getOptionValues("r");
            this.match = false;
            this.fullMatch = false;
            this.regular = true;
            System.out.println("We try with regular");
        }
        if (commandLine.hasOption("o")) {
            String[] arguments = commandLine.getOptionValues("o");
            this.output = arguments[0];
            System.out.println("We try to output with: " + arguments[0]);
        }
    }
}

