package ru.job4j.io_pimalex.search;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;


/*экземпляр типа FileVisitor, будет определять поведение при обходе дерев*/
/*необходимо реализовать интерфейс FileVisitor, чтобы передать соответствующий объект в
метод walkFileTree(). Но если необходимости реализовывать все четыре метода этого интерфейса
нет, то можно просто расширить реализацию класса SimpleFileVisitor,
переопределив лишь необходимые методы.*/
public class PrintFiles implements FileVisitor<Path> {

    /*выполняется перед достуом к элементам каталога*/
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    /*выполняется при доступе к файлу*/
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file.toAbsolutePath());
        return CONTINUE;
    }

    /*выполняется, если к файлу нет доступа*/
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    /*выполняется, когда все элементы директории пройдены*/
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }
}
