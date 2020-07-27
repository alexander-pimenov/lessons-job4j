package ru.job4j.io_pimalex.search_by_criteria_v1;

import com.google.common.base.Joiner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class SearchApp {
    private final static String LN = System.lineSeparator();

    public static void main(String[] args) {
//        args = new String[]{"java", "-jar", "pack.jar", "-d", "c:/projects/job4j/chapter_001", "-n", ".xml", "-m", "-o", "log_found_files_chapter_001.txt"};
//        args = new String[]{"-d", "c:/projects/lessons-job4j/chapter_001", "-n", ".+\\.class", "-r",  "-o", "log_found_files_chapter_001.txt"};

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean exit = false;
        ArgsParser argsParser = null;

        do {
            printMenu();
            System.out.println("If you change your mind to act, then press e(xit) to exit");
            System.out.println("Or act as prompted");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("e") || input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye");
                exit = true;
            } else {
                System.out.println("continue");
                String[] split = input.split("\\s+");
                try {
                    argsParser = new ArgsParser(split);
//            argsParser = new ArgsParser(args);
                    List<Path> files = SearchAndSave.search(Paths.get(argsParser.getDirectory()), argsParser.flagCheck());
//                    Search.save(files, argsParser.getOutput());
                    SearchAndSave.writeResult(files, argsParser.getOutput());

//                    Search.search(Paths.get(argsParser.getDirectory()), argsParser.flagCheck())
//                            .forEach(System.out::println);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } while (!exit);
//        args = new String[]{"-d", "c:/projects/lessons-job4j/chapter_001", "-n", "B.+", "-r", "-o", "log_found_files_chapter_001.txt"};


//        System.out.println(argsParser.getDirectory());
//        System.out.println(argsParser.getSearchFile());
//        System.out.println(argsParser.getOutput());
//        System.out.println(argsParser.flagCheck());

//        System.out.println(argsParser.arguments);
//        Search search = new Search();


    }

    private static void printMenu() {
        System.out.println(
                Joiner.on(LN).join(
                        "Enter command line arguments like this: ",
                        "-d directoryForSearch -n criteriaForSearch -[mfr] -o fileForSaveResult",
                        "Key -d: search directory",
                        "the directory in which the search will be performed must match the format:",
                        "C:/.../...",
                        "Key -n: file name (mask, ful name, regular expression)",
                        "Key -m: Search by mask of file",
                        "Key -f: Search by a complete match for a file name",
                        "Key -r: Search by regular expression",
                        "Key -o: file to write\r"
                )
        );

//        System.out.println("Enter command line arguments like this: ");
//        System.out.println("-d directoryForSearch -n criteriaForSearch -[mfr] -o fileForSaveResult");
//        System.out.println("Key -d: search directory");
//        System.out.println("Key -n: file name (mask, ful name, regular expression)");
//        System.out.println("Key -m: Search by mask of file");
//        System.out.println("Key -f: Search by a complete match for a file name");
//        System.out.println("Key -r: Search by regular expression");
//        System.out.println("Key -o: file to write\r");
    }
}

//-d c:/projects/lessons-job4j/chapter_001 -n B.+ -r -o log_found_files_chapter_004.txt