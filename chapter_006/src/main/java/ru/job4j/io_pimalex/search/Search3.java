package ru.job4j.io_pimalex.search;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Predicate;

public class Search3 {
    public static void main(String[] args) throws IOException {

        /*Используя start или start2 можно получать название файлов с относительным путем (
        путь от каталога) или абсолютным путем (путь от корневого диска С:\)*/
        Path start = Paths.get(".");
        Path start2 = Paths.get(".").toAbsolutePath();
        Path start3 = Paths.get("./chapter_005").toAbsolutePath();
        Path start4 = Paths.get("./chapter_004").toAbsolutePath();
        Search3 search3 = new Search3();

//        long before1 = System.currentTimeMillis(); //проверка времени работы метода: старт
//        search3.search(start2, s -> s.endsWith(".csv")
//                //||s.endsWith(".sample")
//        ).forEach(System.out::println);
//        long after1 = System.currentTimeMillis(); //проверка времени работы метода: финиш
//        System.out.println("spend time = " + (after1 - before1) + "=======================");

        long before11 = System.currentTimeMillis(); //проверка времени работы метода: старт
        search3.search(start3, s -> s.endsWith(".java")
//                || s.endsWith(".sample")
        ).forEach(System.out::println);
        long after11 = System.currentTimeMillis(); //проверка времени работы метода: финиш
        System.out.println("spend time = " + (after11 - before11) + "=======================");

        ExtensionPredicate predicate = new ExtensionPredicate(".java");
        long before22 = System.currentTimeMillis(); //проверка времени работы метода: старт
        search3.search(start3, predicate).forEach(System.out::println);
        long after22 = System.currentTimeMillis(); //проверка времени работы метода: старт
        System.out.println("spend time = " + (after22 - before22) + "=======================");

        long before33 = System.currentTimeMillis(); //проверка времени работы метода: старт
        search3.files(start3.toString(), s -> s.endsWith(".java")).forEach(System.out::println);
        long after33 = System.currentTimeMillis(); //проверка времени работы метода: старт
        System.out.println("spend time = " + (after33 - before33) + "=======================");

//        long before2 = System.currentTimeMillis();
//        searchFiles1(start2, s -> s.endsWith(".csv")
//                //|| s.endsWith(".sample")
//        ).forEach(System.out::println);
//        long after2 = System.currentTimeMillis();
//        System.out.println("spend time = " + (after2 - before2) + "========================");

//        Search3 search33 = new Search3();
//        long before3 = System.currentTimeMillis();
////        search3.filter(start.toString(), List.of(".csv", ".sample")).forEach(System.out::println);
//        search33.filter(start2.toString(), List.of(".csv")).forEach(System.out::println);
//        long after3 = System.currentTimeMillis();
//        System.out.println("spend time = " + (after3 - before3) + "========================");
    }

//    public static List<Path> search(Path root, String ext) throws IOException {
////        SearchFiles3 searcher = new SearchFiles3(p -> p.toFile().getName().endsWith(ext));
//        SearchFiles3 searcher = new SearchFiles3(p -> p.endsWith(ext));
//        Files.walkFileTree(root, searcher);
//        return searcher.getPaths();
//    }

    /*Метод возвращающий полное название файла (т.е. содержит путь к нему).
     * Изменив return можно получать только название файлов без пути к ним*/
    public List<Path> search(Path root, Predicate<String> condition) throws IOException {
//        SearchFiles3 searcher = new SearchFiles3(p -> p.toFile().getName().endsWith(ext));
        SearchFiles3 searcher = new SearchFiles3(condition);
        Files.walkFileTree(root, searcher);
//        return searcher.getPaths();
        return searcher.getFullPaths();
    }

    public List<File> files(String root, Predicate<String> condition) {
        List<File> result = new ArrayList<>();
        Queue<File> list = new LinkedList<>();
        list.offer(new File(root));
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

    /*Метод возвращающий полное название файла (т.е. содержит путь к нему).*/
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
        }*/

    /*Метод возвращающий полное название файла (т.е. содержит путь к нему).*/
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
    private List<Path> paths = new ArrayList<>(); //собирает имя искомого файла
    private List<Path> fullPaths = new ArrayList<>(); //собирает название файла и путь к нему

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
//        if (condition.test(path.getFileName().toString())) { //можно и так записать условие

            //Path parent = path.getParent();
            //System.out.println("Directory name:" + parent);
            //Path fileName = path.getFileName();
            //System.out.println("Matching file:" + fileName);

            paths.add(name); //собирает только имя файла
            fullPaths.add(path); //собирает полный путь файла
        }
    }
}

class ExtensionPredicate implements Predicate<String> {

    private String pattern;

    ExtensionPredicate(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean test(String string) {
        return string.endsWith(pattern);
    }
}

class ExtensionsPredicates implements Predicate<Path> {

    private List<String> extensions;

    public ExtensionsPredicates(List<String> extensions) {
        this.extensions = extensions;
    }

    @Override
    public boolean test(Path path) {
        return extensions.stream().anyMatch(ext -> path.toFile().getName().endsWith(ext));
    }

    public boolean check(Path path, List<String> ext) {
        boolean result = true;
        for (String s : ext) {
            if (path.getFileName().endsWith(s)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
