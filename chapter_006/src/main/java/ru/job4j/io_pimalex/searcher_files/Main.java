package ru.job4j.io_pimalex.searcher_files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Поиск заданных файлов в папках - это алгоритм обхода дерева вглубину
 * с помощью рекурсии.
 */

public class Main {
    public static void main(String[] args) {
        //создаем список куда будем записывать найденные файлы
        List<File> fileList = new ArrayList<>();
        searchFiles(new File("C:/projects/"), fileList);
        //выведем что нашли
        for (File file : fileList) {
//            System.out.println(file);
            System.out.println(file.getAbsolutePath());
        }
    }

    /**
     * С каждой папкой нужно делать одну и ту же опрецию:
     * зайти внутрь и посмотреть заданные файлы.
     * Для этой цели подойдет рекурсия.
     * root - 1 - папка с которой начинаем поиск
     * fileList - 2 - список в который будут добавляться найденные файлы
     */
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
                        if (file.getName().toLowerCase().endsWith(".png")) {
                            fileList.add(file);
                        }
                    }
                }
            }
        }
    }
}
