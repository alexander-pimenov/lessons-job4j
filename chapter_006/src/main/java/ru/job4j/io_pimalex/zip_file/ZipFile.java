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
        FileOutputStream fos = new FileOutputStream("compressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);

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
    }
}
