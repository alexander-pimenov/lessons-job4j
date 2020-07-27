package ru.job4j.io_pimalex.filevisitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Search {
    public static List<String> search(Path root, String ext) throws IOException {
        List<String> result = new ArrayList<>();

        PrintFiles printFiles = new PrintFiles(ext, result);
        Files.walkFileTree(root, printFiles);

        return result;
    }

    /*метод взят у Сергея Архипова*/
    private static void searchFiles(File rootFile, List<File> fileList) {
        if (rootFile.isDirectory()) { //проверка что корневая папка это папка, если это не папка, то и искать там не надо
            /*добавим вывод папки, чтобы видеть как алгоритм перемещается по папкам*/
//            System.out.println("Searching at: "+rootFile.getAbsolutePath());
            /*получим все файлы лежащие внутри папки*/
            File[] directoryFiles = rootFile.listFiles();
            /*если удалось получить эти файлы*/
            if (directoryFiles != null) {
                /*то для каждого файла проверим*/
                for (File file : directoryFiles) {
                    if (file.isDirectory()) { //если файл является папкой
                        //то функция вызывает сама себя, чтобы поискать подпапки
                        searchFiles(file, fileList); //ищем в подпапке, а список передаем тот же что
                        //и в начале, чтобы к нему добавились файлы, которые лежат внутри  этой подпапки
                    } else {
                        //если файл не является подпапкой
                        //тогда проверим условия по которому ищем
                        //и добавим в список, если условие выполнено
                        if (file.getName().toLowerCase().endsWith(".xml")) {
                            fileList.add(file);
                        }
                    }
                }
            }
        }
    }



    public static List<File> findFiles(Path root, List<String> ext) throws IOException {
        //String directory = argZip.getDirectory();
        //String directory = root;
        //List<String> exclude = argZip.getExclude();
        //List<String> exclude;
        List<File> result = new ArrayList<>();
        Queue<File> list = new LinkedList<>();
//        list.offer(new File(directory));
        list.offer(root.toFile());
        while (!list.isEmpty()) {
            File file = list.poll();
            if (file != null && file.isDirectory() && file.canRead()) {
                list.addAll(Arrays.asList(Objects.requireNonNull(file.listFiles())));
            } else if (checkName(file, ext)) {
                result.add(file);
            }
        }
        return result;
    }

    private static boolean checkName(File file, List<String> exclude) {
//        boolean result = true;
        boolean result = false;
        for (String exc : exclude) {
            if (file.getName().endsWith(exc)) {
//                result = false;
                result = true;
                break;
            }
        }
        return result;
    }

    public static List<String> findFilesName(Path root, List<String> ext) throws IOException {
        //String directory = argZip.getDirectory();
        //String directory = root;
        //List<String> exclude = argZip.getExclude();
        //List<String> exclude;
        List<String> result = new ArrayList<>();
        Queue<File> list = new LinkedList<>();
//        list.offer(new File(directory));
        list.offer(root.toFile());
        while (!list.isEmpty()) {
            File file = list.poll();
            if (file != null && file.isDirectory() && file.canRead()) {
                list.addAll(Arrays.asList(Objects.requireNonNull(file.listFiles())));
            } else if (checkFileName(file, ext)) {
                result.add(file.getName());
            }
        }
        return result;
    }

    private static boolean checkFileName(File file, List<String> exclude) {
//        boolean result = true;
        boolean result = false;
        for (String exc : exclude) {
            if (file.getName().endsWith(exc)) {
//                result = false;
                result = true;
                break;
            }
        }
        return result;
    }



    public static void main(String[] args) throws Exception {
        String path = "C:/projects/lessons-job4j/chapter_001";
        /*----------------------------------------------------*/
        search(Paths.get(path), "xml")
                .forEach(System.out::println);
        System.out.println("\n===============================\n");

        //создаем список куда будем записывать найденные файлы
        List<File> fileList = new ArrayList<>();
        searchFiles(new File(path), fileList);
        //выведем что нашли
        for (File file : fileList) {
//            System.out.println(file);
            System.out.println(file.getAbsolutePath());
        }
        System.out.println("\n===============================\n");
        findFiles(Paths.get(path), List.of("xml")).forEach(System.out::println);

        System.out.println("\n===============================\n");
        findFilesName(Paths.get(path), List.of("xml")).forEach(System.out::println);


    }
}
