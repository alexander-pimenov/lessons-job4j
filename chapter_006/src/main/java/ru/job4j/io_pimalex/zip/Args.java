package ru.job4j.io_pimalex.zip;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Args {
    private CommandLine parser;

    public Args(String[] args) {
        Options options = new Options();
        Option optionDirectory = Option.builder("d")
                .required()
                .hasArg()
                .desc("The directory we want to archive")
                .build();
        Option optionOutput = Option.builder("o")
                .required()
                .hasArg()
                .desc("Archive name")
                .build();
        Option optionExclude = Option.builder("e")
                .hasArg()
                .valueSeparator(',')
                .desc("Excluded files from the archive")
                .build();
        options.addOption(optionDirectory);
        options.addOption(optionOutput);
        options.addOption(optionExclude);
        try {
            parser = new DefaultParser().parse(options, args);
        } catch (ParseException e) {
            System.err.println("Parsing failed. " + e.getMessage());
            //e.printStackTrace();
        }
    }

    public String getDirectory() {
        return parser.getOptionValue("d");
    }

    public String getOutput() {
        return parser.getOptionValue("o");
    }

    public List<String> getExclude() {
        return parser.getOptionValue("e") != null ? Arrays.asList(parser.getOptionValues("e")) : new ArrayList<>();
    }
}
