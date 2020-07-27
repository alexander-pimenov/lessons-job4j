package ru.job4j.io_pimalex.filevisitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class PrintFiles implements FileVisitor<Path> {
    private String partOfName;
    private List<String> foundFiles;

    public PrintFiles(String partOfName, List<String> list) {
        this.partOfName = partOfName;
        this.foundFiles = list;
    }

    /*выполняется перед достуом к элементам каталога*/
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    /**
     * boolean isRegularFile(Path)	Путь – это файл?
     *
     * @param file  путь старта
     * @param attrs - кусок имени файла
     * @return - лист имен файлов оканчивающихся на attrs
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!attrs.isRegularFile()) {
            return CONTINUE;
        }
        if (!file.getFileName().toString().endsWith(this.partOfName)) {
            return CONTINUE;
        }
        foundFiles.add(file.getFileName().toString());
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
