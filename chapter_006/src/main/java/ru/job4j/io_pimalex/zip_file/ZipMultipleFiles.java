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
        List<String> srcFiles = Arrays.asList("testFoZip11.txt", "testForZip22.txt");
        FileOutputStream fos = new FileOutputStream("multiCompressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (String srcFile : srcFiles) {
            File fileToZip = new File(srcFile);
            //Если файла нет, то создадим его и что-нибудь запишем в него.
            if (!fileToZip.exists()) {
                fileToZip.createNewFile(); // создание отдельного файла
                FileWriter fw = new FileWriter(fileToZip);
                String line = "Всем привет! \nЖелаю хорошего настроения и успехов в делах!\n";
                fw.write(line);
                fw.close();
            }
            FileInputStream fis = new FileInputStream(fileToZip);
            System.out.println("=== " + fileToZip.getName() + " ===");
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());

            zipOut.putNextEntry(zipEntry);

            /*1-й способ:
            считываем содержимое файла в массив byte*/
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            /*добавляем содержимое к архиву*/
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
    }
}
