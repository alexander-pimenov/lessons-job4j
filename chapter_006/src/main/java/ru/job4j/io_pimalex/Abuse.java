package ru.job4j.io_pimalex;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * Рассмотрим задачу по удалению из файла запрещенных слов.
 */
public class Abuse {
    /**
     * Метод принимает два файла и список слов для удаления.
     * List<String> words - список слов для удаления
     */
    public static void drop(String source, String target, List<String> words) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            in.lines()
                    .flatMap(line -> Stream.of(line.split("\\s+"))) // "\\s+" - пробел или несколько пробелов
                    .filter(word -> !words.contains(word)).map(word -> word + " ") // меняем запрещенноне слово на пробел
                    .forEach(out::print);
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("sourceM.txt"))) {
            out.println("hello foolish dude foolish mam duke foolish dude");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("targetM.txt"))) {
            drop("sourceM.txt", "targetM.txt", List.of("foolish", "dude"));
            reader.lines().forEach(System.out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
