package ru.job4j.io_pimalex.zip_file;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/*как сжать несколько файлов в один файл. Мы сжимаем test1.txt и test2.txt в multiCompressed.zip
 * https://www.codeflow.site/ru/article/java-compress-and-uncompress*/
public class ZipMultipleFiles {
    public static void main(String[] args) throws IOException {
        List<String> srcFiles = Arrays.asList("testForZip11.txt", "testForZip22.txt", "testForZip33.txt");

        /*Настраиваем поток для записи данных в zip-архив:
         * "multiCompressed.zip" - в какой файл и zip-стрим*/
        FileOutputStream fos = new FileOutputStream("multiCompressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);


        for (String srcFile : srcFiles) {
            /*Создаем файл для архивирования*/
            File fileToZip = new File(srcFile);
            //Если файла нет, то создадим его и что-нибудь запишем в него.
            if (!fileToZip.exists()) {
                fileToZip.createNewFile(); // создание отдельного файла
                FileWriter fw = new FileWriter(fileToZip);
                String line = "Всем привет! \nЖелаю хорошего настроения и успехов в делах!\n";
                fw.write(line);
                fw.close();
            }

            /*Создаем объект ZipEntry с именем файла для архивации и добавляем
             * его в записывающий zip-поток ZipOutputStream*/
            System.out.println("=== " + fileToZip.getName() + " ===");
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            //вычитываем файл
            FileInputStream fis = new FileInputStream(fileToZip);

            /*1-й способ:
            считываем содержимое файла в массив byte*/
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            /*Добавляем содержимое к архиву*/
            zipOut.write(buffer);

            /*2-й способ:
            считываем содержимое файла в массив byte*/
//            byte[] buffer = new byte[1024];
//            int length;
//            while ((length = fis.read(buffer)) >= 0) {
//            /*добавляем содержимое к архиву*/
//                zipOut.write(buffer, 0, length);
//            }
            fis.close();
        }
        zipOut.close();
        fos.close();

        //System.out.println("===2-й метод===");
        packFiles(List.of(new File("testForZip11.txt"),
                new File("testForZip22.txt"), new File("testForZip33.txt")),
                new File("zipMultiCompressed.zip"));
    }

    /*Метод упаковывающий в архив несколько файлов.*/
    static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {

            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(file)
                )) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
