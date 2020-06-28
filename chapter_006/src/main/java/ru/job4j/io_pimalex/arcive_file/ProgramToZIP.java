package ru.job4j.io_pimalex.arcive_file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* ##### запаковка в архив #####
 * Для создания архива используется класс ZipOutputStream.
 * Для создания объекта ZipOutputStream в его конструктор передается поток вывода:
 * ZipOutputStream(OutputStream out)
 * Для записи файлов в архив для каждого файла создается объект ZipEntry, в
 * конструктор которого передается имя архивируемого файла. А чтобы добавить
 * каждый объект ZipEntry в архив, применяется метод putNextEntry().
 * После добавления объекта ZipEntry в поток нам также надо добавить в него
 * и содержимое файла. Для этого используется метод write, записывающий в поток
 * массив байтов: zout.write(buffer);.
 * В конце надо закрыть ZipEntry с помощью метода closeEntry().
 * После этого можно добавлять в архив новые файлы - в этом случае все
 * вышеописанные действия для каждого нового файла повторяются.
 * https://metanit.com/java/tutorial/6.12.php*/
public class ProgramToZIP {
    public static void main(String[] args) {
        String filename = "c:/test/SomeDir/notes.txt";
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("c:/test/SomeDir/output.zip"));
             FileInputStream fis = new FileInputStream(filename)) {
            ZipEntry entry1 = new ZipEntry("notes.txt");
            zout.putNextEntry(entry1);
            //считываем содержимое файла в массив byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // добавляем содержимое к архиву
            zout.write(buffer);
            // закрываем текущую запись для новой записи
            zout.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
