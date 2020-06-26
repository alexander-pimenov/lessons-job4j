package ru.job4j.io_pimalex.search;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Test13 {
    public static void main(String[] args) {
//        Path startPath = Paths.get("Введите сюда путь к каталогу для поиска (желательно, чтобы он содержал java-файлы)");
        Path startPath = Paths.get(".");
        Path pathSource = Paths.get(".");

//        try {
//            Files.walkFileTree(pathSource, new MyFileVisitor());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("##########################################");

        //Строка с glob-шаблоном
//        String pattern = "glob:*.java";
        String patternXML = "glob:*.xml";
        String patternJS = "glob:*.js";
        String patternHTML = "glob:*.html";
        String patternCSV = "glob:*.csv";

        //Строка с regex-шаблоном
        //String pattern = "regex:\\S+\\.java";

        try {
            Files.walkFileTree(startPath, new MyFileFindVisitor(patternCSV));
            System.out.println("File search completed!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*Необходимо реализовать интерфейс FileVisitor, чтобы передать соответствующий
 * объект в метод walkFileTree(). Но если необходимости реализовывать все четыре
 * метода этого интерфейса нет, то можно просто расширить реализацию класса
 * SimpleFileVisitor, переопределив лишь необходимые методы.*/
class MyFileVisitor extends SimpleFileVisitor<Path> {
    /*метод для вывода имени файла*/
    public FileVisitResult visitFile(Path path,
                                     BasicFileAttributes fileAttributes) {
        System.out.println("file name:" + path.getFileName());
        return FileVisitResult.CONTINUE;
    }

    /*метод для вывода имени директории*/
    public FileVisitResult preVisitDirectory(Path path,
                                             BasicFileAttributes fileAttributes) {
        System.out.println("Directory name:" + path);
        return FileVisitResult.CONTINUE;
    }
}

class MyFileFindVisitor extends SimpleFileVisitor<Path> {
    private PathMatcher matcher;

    public MyFileFindVisitor(String pattern) {
        try {
            matcher = FileSystems.getDefault().getPathMatcher(pattern);
        } catch (IllegalArgumentException iae) {
            System.err
                    .println("Invalid pattern; did you forget to prefix \"glob:\" or \"regex:\"?");
            System.exit(1);
        }
    }

    public FileVisitResult visitFile(Path path,
                                     BasicFileAttributes fileAttributes) {
        //System.out.println("file name:" + path.getFileName());
        find(path);
        return FileVisitResult.CONTINUE;
    }

    private void find(Path path) {
        Path name = path.getFileName();
        if (matcher.matches(name)){
            System.out.println("Directory name:" + path.getParent());
            System.out.println("Matching file:" + path.getFileName());
        }

    }

    public FileVisitResult preVisitDirectory(Path path,
                                             BasicFileAttributes fileAttributes) {
        //System.out.println("Directory name:" + path);
        find(path);
        return FileVisitResult.CONTINUE;
    }
}
