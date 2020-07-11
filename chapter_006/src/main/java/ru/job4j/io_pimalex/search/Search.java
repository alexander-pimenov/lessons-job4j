package ru.job4j.io_pimalex.search;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        /*Программа выводит содержимое всей директории.*/
        /*Используя start или start2 можно получать название файлов с относительным путем (
        путь от каталога) или абсолютным путем (путь от корневого диска С:\)*/
        Path start = Paths.get(".");
        Path start2 = Paths.get(".").toAbsolutePath();

        search(start2, "csv").forEach(System.out::println);
        System.out.println("====================");
        search2(start2, "csv").forEach(p -> System.out.println(p.toString()));
    }

    /*Метод возвращающий одну позицию: название файла. */
    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(ext);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    /*Метод возвращающий две позиции: название файла и путь к нему. */
    public static List<Searcher> search2(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(ext);
        Files.walkFileTree(root, searcher);
        return searcher.getSearchers();
    }
}

class SearchFiles extends SimpleFileVisitor<Path> {
    private PathMatcher matcher;
    private List<Path> paths = new ArrayList<>();
    private List<Searcher> searchers = new ArrayList<>();

    public SearchFiles(String ext) {
        String pattern = "glob:*." + ext;
        try {
            this.matcher = FileSystems.getDefault().getPathMatcher(pattern);
        } catch (IllegalArgumentException iae) {
            System.err.println("Invalid pattern; did you forget to prefix \"glob:\" or \"regex:\"?");
            System.exit(1);
        }
    }

    public List<Path> getPaths() {
        return paths;
    }

    public List<Searcher> getSearchers() {
        return searchers;
    }

    public FileVisitResult visitFile(Path path,
                                     BasicFileAttributes fileAttributes) {
        find(path);
        return FileVisitResult.CONTINUE;
    }

    private void find(Path path) {
        Path name = path.getFileName();
        if (matcher.matches(name)) {
            Path parent = path.getParent();
            //System.out.println("Directory name:" + parent);
            Path fileName = path.getFileName();
            //System.out.println("Matching file:" + fileName);

            paths.add(name);
            searchers.add(new Searcher(parent.toString(), fileName.toString()));
        }
    }

    public FileVisitResult preVisitDirectory(Path path,
                                             BasicFileAttributes fileAttributes) {
        find(path);
        return FileVisitResult.CONTINUE;
    }
}

class Searcher {
    private String nameDir;
    private String nameFile;

    public Searcher(String nameDir, String nameFile) {
        this.nameDir = nameDir;
        this.nameFile = nameFile;
    }

    public String getNameDir() {
        return nameDir;
    }

    public String getNameFile() {
        return nameFile;
    }

    @Override
    public String toString() {
        return "{" +
                "nameDir='" + nameDir + '\'' +
                ", nameFile='" + nameFile + '\'' +
                '}';
    }
}