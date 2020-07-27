package ru.job4j.io_pimalex.search_by_criteria_v2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static List<Path> search(Path root, Predicate<Path> predicate) throws IOException {
        SearchFiles searcher = new SearchFiles(predicate);
        Files.walkFileTree(root, searcher);
        return searcher.getPath();
    }

    public static void save(List<Path> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            for (Path str : log) {
                out.write(str.getFileName() + System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        args = new String[]{"java", "-jar", "pack.jar", "-d", "c:/projects/job4j/chapter_004", "-n", "class", "-m","-o", "log_chapter_004.txt"};
//        args = new String[]{"-d", "c:/projects/job4j/chapter_004", "-n", ".class", "-m","-o", "log_chapter_004.txt"};
//        ArgSearch argSearch = new ArgSearch(args); //  startup options "-d C:/projects/job4j/chapter_002 -n *.txt -r -o logresult.txt"
//        List<Path> list = Search.search(Paths.get(argSearch.directory()), argSearch.exclude());
//        save(list, argSearch.output());

        Search.search(Paths.get("C:/projects/lessons-job4j/chapter_001"), ext -> ext.endsWith("xml")).forEach(System.out::println);

    }
}
//C:\projects\lessons-job4j\chapter_001\pom.xml
//C:\projects\lessons-job4j\chapter_001\target\checkstyle-checker.xml
//C:\projects\lessons-job4j\chapter_001\target\checkstyle-result.xml
//C:\projects\lessons-job4j\chapter_001\target\classes\META-INF\maven\ru.job4j\chapter_001\pom.xml

//pom.xml
//checkstyle-checker.xml
//checkstyle-result.xml
//pom.xml


