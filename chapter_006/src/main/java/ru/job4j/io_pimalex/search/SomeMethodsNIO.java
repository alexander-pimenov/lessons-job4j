package ru.job4j.io_pimalex.search;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class SomeMethodsNIO {
    public static void main(String[] args) throws IOException {
        /*Программа выводит содержимое всей директории.*/
        //Path start = Paths.get(".");
        Path start = Paths.get("C:\\test\\staff.json");
        System.out.println(start.getFileName());
        System.out.println(start.getRoot());
        System.out.println(start.toUri());
        System.out.println(start.toAbsolutePath());
        System.out.println("================");
        boolean bool = start.toString().endsWith(".json");
        System.out.println(bool);
        boolean b1 = start.endsWith(".json");
        boolean b2 = start.endsWith(".js");
        System.out.println(b1);
        System.out.println(b2);

        int count = start.getNameCount();
        System.out.println(count);

        System.out.println(start.getName(0));
        System.out.println(start.getName(1));
        System.out.println("================");

        Iterator<Path> iterator = start.iterator();
        while (iterator.hasNext()) {
            Path next = iterator.next();
            System.out.println(next);
            String s = next.toString();
            if (s.endsWith(".json")) {
                System.out.println("конец на .json");
            } else {
                System.out.println("не верный конец");
            }
        }
        System.out.println("================");

        for (Path el : start) {
            System.out.println(el);
        }

        /*Как альтернативу можно использовать обычный цикл и методы
        getNameCount() (для получения числа элементов в пути) и
        getName(index) (для получения элемента по индексу).*/
        //Path path = Files.walkFileTree(start, new PrintFiles());
        System.out.println("================");
        //System.out.println(path);

    }
}
