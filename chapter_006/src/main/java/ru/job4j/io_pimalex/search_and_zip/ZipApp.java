package ru.job4j.io_pimalex.search_and_zip;

import java.util.List;

public class ZipApp {
    public static void main(String[] args) {
        Search search = new Search();
        args = new String[]{"java", "-jar", "pack.jar", "-d", "c:/projects/lessons-job4j/chapter_004", "-e", "java,xml", "-o", "project_chapter_004.zip"};
        Args argZip = new Args(args);
        Zip zip = new Zip();

        String directory = argZip.directory();
        System.out.println(directory);

        String output = argZip.output();
        System.out.println(argZip.output());

        List<String> extensions = argZip.extensions();
        System.out.println(extensions);
        System.out.println("=================");
        search.files(directory, List.of(".java", ".xml"), false)
                .forEach(System.out::println);

        zip.zip();


    }
}
