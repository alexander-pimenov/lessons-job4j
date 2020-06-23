package ru.job4j.io_pimalex;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
//    @Rule
//    public TemporaryFolder folder = new TemporaryFolder();
//
//    @Test
//    public void whenServerUnavailable() throws IOException {
//        Analizy analizy = new Analizy();
//        File source = folder.newFile("source.txt");
//        File target = folder.newFile("target.txt");
//        try (PrintWriter out = new PrintWriter(source)) {
//            out.println("500 13:56:01");
//            out.println("300 14:57:01");
//            out.println("400 15:58:01");
//            out.println("500 20:59:01");
//            out.println("200 22:01:02");
//            out.println("100 23:02:02");
//        }
//        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
//        StringBuilder rsl = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
//            reader.lines().map(el -> el + " ").forEach(rsl::append);
//        }
//        assertEquals(rsl.toString(), "13:56:01;14:57:01 15:58:01;22:01:02 ");
//    }

    private Analizy analizy;
    private String tmp = null; //используем просто чтобы было видно появившиеся файлы. nullserver.log nulltarget.csv

    /*До тестируемого метода мы создаем лог файл и записываем в него
     * дынные по работе сервера.
     * Добавляем tmp=null к названию фала, чтоб выделялся*/
    @Before
    public void setUp() {
        analizy = new Analizy();
        //tmp = System.getProperty("java.io.tmpdir");
        try (PrintWriter out = new PrintWriter(new FileOutputStream(tmp + "server.log"))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenServerUnavailable() {
        analizy.unavailable(tmp + "server.log", tmp + "target.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(tmp + "target.csv"))) {
            assertThat(reader.readLine(), is("10:57:01;10:59:01"));
            assertThat(reader.readLine(), is("11:01:02;11:02:02"));
            assertNull(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Полсе проведения теста удаляем файлы server.log и target.csv
     * Вернее nullserver.log и nulltarget.csv*/
    @After
    public void after() {
        File file = new File(tmp + "server.log");
        if (file.exists()) {
            file.delete();
        }
        file = new File(tmp + "target.csv");
        if (file.exists()) {
            file.delete();
        }
    }
}