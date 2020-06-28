package ru.job4j.io_pimalex.arcive_file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*##### распаковка архива #####
 * Для считывания файлов из архива ZipInputStream использует
 * метод getNextEntry(), который возвращает объект ZipEntry.
 * Объект ZipEntry представляет отдельную запись в zip-архиве.
 * Например, считаем какой-нибудь архив.
 * ZipInputStream в конструкторе получает ссылку на поток ввода.
 * И затем в цикле выводятся все файлы и их размер в байтах, которые
 * находятся в данном архиве. Затем данные извлекаются из архива и
 * сохраняются в новые файлы, которые находятся в той же папке и
 * которые начинаются с "new".
 * https://metanit.com/java/tutorial/6.12.php*/
public class ProgramUnZIP {
    public static void main(String[] args) {

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream("C:\\test\\SomeDir\\output.zip"))) {
            ZipEntry entry;
            String name;
            long size = 0;
            while ((entry = zin.getNextEntry()) != null) {

                name = entry.getName(); // получим название файла
                size = entry.getSize();  // получим его размер в байтах
                System.out.printf("File name: %s \t File size: %d \n", name, size);

                // ##### распаковка #####
                FileOutputStream fout = new FileOutputStream("C:\\test\\SomeDir\\new" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }
}
