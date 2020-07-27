package ru.job4j.io_pimalex.search_by_criteria_v1;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchAndSave {
    private final static String LN = System.lineSeparator();

    public static void main(String[] args) {
        args = new String[]{"java", "-jar", "pack.jar", "-d", "c:/projects/job4j/chapter_004", "-e", "class", "-o", "log_found_files_chapter_004.txt"};

        String path = "c:/projects/lessons-job4j/chapter_001";
        String log = "logSample1.txt";

//        Path path = Paths.get(".").toAbsolutePath();


        List<Path> pathList = null;
        try {
            pathList = search(Paths.get(path), s ->
                    s.equals("pom.xml")
                            || s.endsWith(".xml")
                            || s.contains("xml")
            )
//                                .forEach(System.out::println)
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        save(pathList, log);
//            writeResult(pathList, log); //можно использовать этот метод для записи файлов
    }


    /*Метод записывающий найденные файлы в файл-лог на компьютере, 1-й способ*/
    public static void save(List<Path> listFiles, String fileLog) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(fileLog)))) {
            if (listFiles != null && !listFiles.isEmpty()) {
                for (Path str : listFiles) {
                    out.write(str.getFileName() + LN);
//                System.out.println("записан в лог: " + str.getFileName());
                }
                System.out.println("Save completed.");
            } else {
                out.write("Файл(ы) не обнаружен(ы)." + LN);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*Метод записи найденных файлов в файл-лог на компьютере, 2-й способ*/
    public static void writeResult(List<Path> listFiles, String fileLog) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileLog))) {
            bw.write("Найдены файлы: " + System.lineSeparator());
            if (listFiles != null && !listFiles.isEmpty()) {
                for (Path str : listFiles) {
                    bw.write(str.getFileName() + LN);
                }
            } else {
                bw.write("Файл(ы) не обнаружен(ы)." + LN);
            }
            System.out.println("Save completed.");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /* Метод возвращающий полное название файла (т.е. содержит путь к нему).
     * В предикате можно указывать несколько условий.
     * Изменив return можно получать только название файлов без пути к ним*/
    public static List<Path> search(Path root, Predicate<String> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getFullPaths();
//        return searcher.getPaths();
    }
}

/*Экземпляр типа FileVisitor, будет определять поведение при обходе дерева.
 * Необходимо реализовать интерфейс FileVisitor, чтобы передать соответствующий объект в
 * метод walkFileTree(). Но если необходимости реализовывать все четыре метода этого интерфейса
 * нет, то можно просто расширить реализацию класса SimpleFileVisitor,
 * переопределив лишь необходимые методы.*/
class SearchFiles extends SimpleFileVisitor<Path> {
    private Predicate<String> condition;
    private List<Path> fullPaths = new ArrayList<>(); //собирает название файла и путь к нему
    private List<Path> paths = new ArrayList<>(); //собирает только имя искомого файла

    SearchFiles(Predicate<String> condition) {
        this.condition = condition;
    }

    public List<Path> getFullPaths() {
        return fullPaths;
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        find(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
//        find(dir);
        return FileVisitResult.CONTINUE;
    }

    private void find(Path path) {
        Path name = path.getFileName();
        if (condition.test(path.toFile().getName())) {

            //Path parent = path.getParent();
            //System.out.println("Directory name:" + parent);
            //Path fileName = path.getFileName();
            //System.out.println("Matching file:" + fileName);

            paths.add(name); //собирает только имя файла
            fullPaths.add(path); //собирает полный путь файла
        }
    }
}

//c:\projects\lessons-job4j\chapter_001\pom.xml
//c:\projects\lessons-job4j\chapter_001\p_xml.txt
//c:\projects\lessons-job4j\chapter_001\target\checkstyle-checker.xml
//c:\projects\lessons-job4j\chapter_001\target\checkstyle-result.xml
//c:\projects\lessons-job4j\chapter_001\target\classes\META-INF\maven\ru.job4j\chapter_001\pom.xml
//c:\projects\lessons-job4j\chapter_001\xml_p.txt