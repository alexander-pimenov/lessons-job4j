package ru.job4j.io_pimalex.search_by_criteria_v2;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class ArgSearch {
    private final String[] args;
    private Map<String, String> arguments = new HashMap<>();

    public ArgSearch(String[] args) {
        this.args = args;
        parseArguments();
    }

    public boolean valid() {
        return args.length == 7 && (args[0].equals("-d") && args[2].equals("-n") && args[5].equals("-o"));
    }

    private void parseArguments() {
        if (!valid()) {
            throw new IllegalArgumentException("Invalid startup options");
        }
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-m") || args[i].equals("-f") || args[i].equals("-r")) {
                arguments.put(args[i], "");
            } else {
                arguments.put(args[i], args[++i]);
            }
        }
    }

    public String directory() {
        return arguments.get("-d");
    }

    public String searchFile() {
        return arguments.get("-n");
    }

    public String output() {
        return arguments.get("-o");
    }

    public Predicate<Path> exclude() {
        Predicate<Path> filePredicate = null;
        if (arguments.containsKey("-r")) {
            filePredicate = path -> (path.getFileName().toString().matches(preparePattern(searchFile())));
        } else if (arguments.containsKey("f")) {
            filePredicate = path -> path.getFileName().toString().equals(searchFile());
        } else if (arguments.containsKey("-m")) {
            filePredicate = path -> path.getFileName().endsWith(searchFile());
        }
        return filePredicate;
    }

    private static String preparePattern(String pattern) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c == '*') {
                sb.append(".*");
            } else if (c == '.') {
                sb.append("\\.");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
