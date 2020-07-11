package ru.job4j.io_pimalex.zip_file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/*
 * Не работает.
 * Сжимаем весь каталог. Мы поместим zipTest в dirCompressed.zip
 * Обратите внимание, что:
 * - Чтобы сжать подкаталоги, мы рекурсивно перебираем их.
 * - Каждый раз, когда мы находим каталог, мы добавляем его имя к потомкам
 * ZipEntry имя для сохранения иерархии.
 * - Мы также создаем запись каталога для каждого пустого каталога
 * https://www.codeflow.site/ru/article/java-compress-and-uncompress*/
public class ZipDirectory {
    public static void main(String[] args) throws IOException {
        /*1-й вариант: для создания одной директории*/
        String sourceFile = "zipTest"; //1-й вариант: для создания одной директории
        String sourceFile2 = "c:/test/SomeDir"; //1-й вариант: для создания одной директории
        String sourceFile3 = "./chapter_005"; //

        /*2-й вариант: для создания структуры вложенных папок*/
        //String sourceFile = "zipTest/Test/ZIP"; //2-й вариант: для создания структуры вложенных папок

        FileOutputStream fos = new FileOutputStream("dirCompressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        ZipOutputStream zipOut2 = new ZipOutputStream(new FileOutputStream("c:/test/dirArchive.zip"));
        ZipOutputStream zipOut3 = new ZipOutputStream(new FileOutputStream("./dirArchiveChapter_005.zip"));

        File fileToZip3 = new File(sourceFile3);
        File fileToZip2 = new File(sourceFile2);
        File fileToZip = new File(sourceFile);
        if (!fileToZip.exists()) {
            fileToZip.mkdir(); //для создания одной диретории
            //fileToZip.mkdirs(); //для создания структуры вложенных папок
        }

        zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipFile(fileToZip2, fileToZip2.getName(), zipOut2);
        zipFile(fileToZip3, fileToZip3.getName(), zipOut3);
        zipOut.close();
        fos.close();
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }
}
