package ru.job4j.io_pimalex;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class AnalizyTest2 {
    private Analizy analizy;
    /*Чтобы создать файлы во временной директории, нужно использовать
     * класс org.unit.rules.TemporaryFolder.
     * Этот класс позволяет создавать файлы и директории во временном каталоге.
     * После запуска теста файлы созданные через TemporaryFolder будут удалены.
     * Теперь нам нет необходимости заботиться о мусоре, который оставляет наш
     * тест, потому что его уберет класс  TemporaryFolder.
     * Важно, класс TemporaryFolder нужно использовать, когда программа
     * записывает результат в файл. */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() {
        analizy = new Analizy();
    }

    @Test
    public void whenServerUnavailable() throws IOException {
        File sourceT = folder.newFile("serverT.log");
        File targetT = folder.newFile("targetT.csv");

        /*Здесь мы создаем файл и заполняем его содержимое.*/
        try (PrintWriter out = new PrintWriter(new FileOutputStream(sourceT))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /*Здесь мы выполняем действие программы, а далее читаем полученный файл.*/
        analizy.unavailable(sourceT.getAbsolutePath(), targetT.getAbsolutePath());

        try (BufferedReader reader = new BufferedReader(new FileReader(targetT))) {
            assertThat(reader.readLine(), is("10:57:01;10:59:01"));
            assertThat(reader.readLine(), is("11:01:02;11:02:02"));
            assertNull(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
