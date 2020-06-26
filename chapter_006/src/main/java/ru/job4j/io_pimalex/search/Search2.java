package ru.job4j.io_pimalex.search;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/*В этом классе, метод search() выводит только названия файлов,
 * которые мы ищем по соответствующему расширению.
 * В отличие от класса Search, который может выводить и просто
 * название файла и путь к этому файлу.*/
public class Search2 {
    public static void main(String[] args) {
    /*Программа выводит содержимое всей директории,
    т.е. ищем файлы по всей директории.*/
        Path start = Paths.get(".");

        try {
            search(start, "csv").forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles2 searcher = new SearchFiles2(ext);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}

/*Экземпляр типа FileVisitor, будет определять поведение при обходе дерева.
 * Необходимо реализовать интерфейс FileVisitor, чтобы передать соответствующий объект в
 * метод walkFileTree(). Но если необходимости реализовывать все четыре метода этого интерфейса
 * нет, то можно просто расширить реализацию класса SimpleFileVisitor,
 * переопределив лишь необходимые методы.*/
class SearchFiles2 extends SimpleFileVisitor<Path> {
    private PathMatcher matcher;
    private List<Path> paths = new ArrayList<>();

    public SearchFiles2(String ext) {
        String pattern = "glob:*." + ext;
        try {
            this.matcher = FileSystems.getDefault().getPathMatcher(pattern);
        } catch (IllegalArgumentException iae) {
            System.exit(1);
        }
    }

    public PathMatcher getMatcher() {
        return matcher;
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        find(file);
        return FileVisitResult.CONTINUE;
    }

    private void find(Path path) {
        Path name = path.getFileName();
        if (matcher.matches(name)) {
            //Path parent = path.getParent();
            //System.out.println("Directory name:" + parent);
            //Path fileName = path.getFileName();
            //System.out.println("Matching file:" + fileName);
            paths.add(name);
        }
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        find(dir);
        return FileVisitResult.CONTINUE;
    }
}
