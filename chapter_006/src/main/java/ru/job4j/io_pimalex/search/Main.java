package ru.job4j.io_pimalex.search;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*пример работы с предикатом*/
public class Main {

    private static List<Path> paths = new ArrayList<>();

    /*Метод рекурсивно обходит каталог с помощью DirectoryStream.
     * Files.newDirectoryStream открывает каталог, возвращая
     * DirectoryStream для перебора всех записей в каталоге.*/
    private static List<Path> walkOnFiles(Path path) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    walkOnFiles(entry);
                }
                paths.add(entry);
            }
        }
        return paths;
    }

    public static void main(String[] args) {

//        Predicate<String> isPositive = x -> x.endsWith("aa");
//        System.out.println(isPositive.test("aaaaqw"));
//        System.out.println(isPositive.test("aaaaaaa"));


        Path dirName = Paths.get("./chapter_005/");
        //В следующем примере мы покажем, как рекурсивно обходить каталог с помощью
        // DirectoryStream.
        try {
            List<Path> paths = walkOnFiles(dirName);
            paths.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Перечисляет текущий каталог, т.е. файлы в нем, без глубинного обхода
        //Files.newDirectoryStream открывает каталог,
        //возвращая DirectoryStream для перебора всех записей в каталоге.
        //Исследует только один уровень в каталоге
        try (var paths1 = Files.newDirectoryStream(dirName)) {
            paths1.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //The second parameter of the Files.newDirectoryStream() is the glob pattern.
        try (var paths2 = Files.newDirectoryStream(dirName, "*.xml")) {
            paths2.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Программа гуляет по каталогу на всех или только выбранных уровнях = maxDepth: 5.
        //Мы применяем фильтр с предикатом Files.isRegular(), чтобы вывести только файлы
        try (Stream<Path> paths = Files.walk(Paths.get(String.valueOf(dirName)))) {
            paths.filter(Files::isRegularFile)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //В следующем примере показаны каталоги в указанном каталоге.
        //На этот раз нет предела рекурсивной ходьбе. (хотя тоже можно указать maxDepth: )
        try (Stream<Path> paths = Files.walk(Paths.get(String.valueOf(dirName)))) {
            paths.filter(Files::isDirectory)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Программа перечисляет файлы XML в каталоге загрузок.
        //Объект пути преобразуется в строку, и мы вызываем метод на строку, чтобы проверить,
        //заканчивается ли он расширением XML.
        try (Stream<Path> paths = Files.walk(Paths.get(String.valueOf(dirName)))) {
            paths.map(path -> path.toString()).filter(f -> f.endsWith(".xml"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Пример перечисляет текущий каталог: все файлы лежащие в нем.
        try {
            Files.list(Paths.get("."))
                    .forEach(path -> System.out.println(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //В примере перечислены каталоги в домашнем каталоге пользователя.
        //Мы преобразуем объект path в файл с помощью toFile () и вызываем метод
        // isDirectory (). Поток фильтруется с помощью фильтра ().
        var homeDir = System.getProperty("user.home");

        try {
            Files.list(new File(homeDir).toPath())
                    .filter(path -> path.toFile().isDirectory())
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Следующая программа перечисляет все файлы PDF в каталоге Downloads
        //Объект пути преобразуется в строку, и мы вызываем метод на строку,
        //чтобы проверить, заканчивается ли он расширением PDF.
        var homeDir2 = System.getProperty("user.home")
                + System.getProperty("file.separator") + "Downloads";

        try {
            Files.list(Paths.get(homeDir2)).filter(path -> path.toString().endsWith(".pdf"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Подсчитаем количество файлов PDF в каталоге Downloads
        long nOfPdfFiles = 0;
        try {
            nOfPdfFiles = Files.list(Paths.get(homeDir2)).filter(path -> path.toString()
                    .endsWith(".pdf")).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("There are %d PDF files", nOfPdfFiles);

        //


    }
}
