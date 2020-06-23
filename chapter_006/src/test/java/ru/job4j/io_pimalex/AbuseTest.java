package ru.job4j.io_pimalex;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class AbuseTest {
    /*Чтобы создать файлы во временной директории, нужно использовать
     * класс org.unit.rules.TemporaryFolder.
     * Этот класс позволяет создавать файлы и
     * директории во временном каталоге.
     * После запуска теста файлы созданные
     * через TemporaryFolder будут удалены.
     * Теперь нам нет необходимости заботиться о мусоре,
     * который оставляет наш тест, потому что его уберет
     * класс  TemporaryFolder.
     * Важно, класс TemporaryFolder нужно использовать,
     * когда программа записывает результат в файл. */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void drop() throws IOException {
        File sourceD = folder.newFile("sourceD.txt");
        File targetD = folder.newFile("targetD.txt");

        /*Здесь мы создаем файл и заполняем его содержимое.*/
        try (PrintWriter out = new PrintWriter(sourceD)) {
            out.println("hello foolish dude");
        }
        /*Здесь мы выполняем действие программы, а далее читаем полученный файл.*/
        Abuse.drop(sourceD.getAbsolutePath(), targetD.getAbsolutePath(),
                List.of("foolish"));
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(targetD))) {
            in.lines().forEach(rsl::append);
        }
        /*В конце мы проверяем результаты.*/
        assertThat(rsl.toString(), is("hello dude "));
    }


}