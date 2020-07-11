package ru.job4j.io_pimalex.zip_file;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/*Давайте сначала посмотрим на простую операцию - архивирование одного файла.
 * Для нашего примера здесь мы заархивируем файл с именем test1.txt в архив с
 * именем compressed.zip .
 * https://www.codeflow.site/ru/article/java-compress-and-uncompress*/
public class ZipFile {
    public static void main(String[] args) throws IOException {

        String sourceFile = "testForZip.txt";
        File fileToZip = new File(sourceFile);
        //Если файла нет, то создадим его и что-нибудь запишем в него.
        if (!fileToZip.exists()) {
            fileToZip.createNewFile(); // создание отдельного файла
            FileWriter fw = new FileWriter(fileToZip);
            String line = "Всем привет! \nЖелаю хорошего настроения и успехов в делах!\n";
            fw.write(line);
            fw.close();
        }
        /*Настраиваем поток для записи данных в zip-архив:
         * "multiCompressed.zip" - в какой файл и zip-стрим*/
        FileOutputStream fos = new FileOutputStream("compressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        /*Создаем объект ZipEntry с именем файла для архивации и добавляем
         * его в записывающий zip-поток ZipOutputStream*/
        /* при создании ZipEntry мы используем относительный путь, а не просто имя файла.
         * Это сделано для того, чтобы при архивации сохранились все дерево директорий,
         * ведущих к файлу. В случае использования f.getName() в архиве просто будет
         * плоский список файлов без информации о директориях*/
        ZipEntry zipEntry = new ZipEntry(fileToZip.getPath());
        //        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());

        zipOut.putNextEntry(zipEntry);

        //вычитываем файл
        FileInputStream fis = new FileInputStream(fileToZip);

        //1-й способ: считываем содержимое файла в массив byte
//        byte[] buffer = new byte[1024];
//        int length;
        //добавляем содержимое к архиву
//        while ((length = fis.read(buffer)) >= 0) {
//            zipOut.write(buffer, 0, length);
//        }
        //2-й способ: считываем содержимое файла в массив byte
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        //добавляем содержимое к архиву
        zipOut.write(buffer);

        zipOut.close();
        fis.close();
        fos.close();

        //System.out.println("===2-й метод===");
        packSingleFile(new File("testForZip.txt"), new File("zipFile.zip"));
//        packSingleFile(fileToZip, new File("zipFile.zip"));

    }

    /*Метод упаковывающий в архив 1 файл.*/
    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target))
        )) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
