package ru.job4j.io_pimalex.search;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Predicate;

public class Search3 {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");

        search(start, s -> s.endsWith(".csv")
                ||s.endsWith(".sample")
        ).forEach(System.out::println);
        System.out.println("========================");

        searchFiles1(start, s -> s.endsWith(".csv")
                || s.endsWith(".sample")
        ).forEach(System.out::println);
        System.out.println("========================");

        Search3 search3 = new Search3();
        search3.filter(start.toString(), List.of(".csv", ".sample")).forEach(System.out::println);
        System.out.println("========================");
    }

//    public static List<Path> search(Path root, String ext) throws IOException {
////        SearchFiles3 searcher = new SearchFiles3(p -> p.toFile().getName().endsWith(ext));
//        SearchFiles3 searcher = new SearchFiles3(p -> p.endsWith(ext));
//        Files.walkFileTree(root, searcher);
//        return searcher.getPaths();
//    }

    public static List<Path> search(Path root, Predicate<String> condition) throws IOException {
//        SearchFiles3 searcher = new SearchFiles3(p -> p.toFile().getName().endsWith(ext));
        SearchFiles3 searcher = new SearchFiles3(condition);
        Files.walkFileTree(root, searcher);
//        return searcher.getPaths();
        return searcher.getFullPaths();
    }

    public static List<File> searchFiles1(Path root, Predicate<String> condition) {
        List<File> result = new ArrayList<>();
        Queue<File> list = new LinkedList<>();
        list.offer(new File(String.valueOf(root)));
        while (!list.isEmpty()) {
            File file = list.poll();
            if (file.isDirectory() && file.canRead()) {
                list.addAll(Arrays.asList(file.listFiles()));
            } else if (condition.test(file.getName())) {
                result.add(file);
            }
        }
        return result;
    }

        /*public File[] listFiles() {
        String[] ss = list();
        if (ss == null) return null;
        int n = ss.length;
        File[] fs = new File[n];
        for (int i = 0; i < n; i++) {
            fs[i] = new File(ss[i], this);
        }
        return fs;
    }
*/

    public List<File> filter(String parent, List<String> exts) {
        List<File> result = new ArrayList<>();
        List<File> preparedList = getFilesFrom(parent);
        for (String value : exts) {
            for (File file : preparedList) {
                if (file.getName().endsWith(value)) {
                    result.add(file);
                }
            }
        }
        return result;
    }

    private List<File> getFilesFrom(String parent) {
        List<File> result = new ArrayList<>();
        File start = new File(parent);
        Queue<File> data = new LinkedList<>();
        File[] deepDirectory = null;
        data.offer(start);
        while (!data.isEmpty()) {
            File file = data.poll();
            if (file != null && file.isDirectory()) {
                deepDirectory = file.listFiles();
                if (deepDirectory != null) {
                    for (File value : deepDirectory) {
                        data.offer(value);
                    }
                }
            } else {
                result.add(file);
            }
        }
        return result;
    }
}

class SearchFiles3 extends SimpleFileVisitor<Path> {

    private Predicate<String> condition;
    private List<Path> paths = new ArrayList<>();
    private List<Path> fullPaths = new ArrayList<>();

    public SearchFiles3(Predicate<String> condition) {
        this.condition = condition;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public List<Path> getFullPaths() {
        return fullPaths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        find(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        find(dir);
        return FileVisitResult.CONTINUE;
    }

    private void find(Path path) {
        Path name = path.getFileName();
        if (condition.test(path.toFile().getName())) {
//        if (condition.test(path.getFileName().toString())) {

            //Path parent = path.getParent();
            //System.out.println("Directory name:" + parent);
            //Path fileName = path.getFileName();
            //System.out.println("Matching file:" + fileName);

            paths.add(name);
            fullPaths.add(path);
        }
    }
}
