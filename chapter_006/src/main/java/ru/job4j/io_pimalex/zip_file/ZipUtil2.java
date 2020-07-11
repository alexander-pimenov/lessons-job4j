package ru.job4j.io_pimalex.zip_file;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/*Хорошая программа для архивирования.
 * Гуляет по всем папкам упаковывая их в указанный архив.*/
/*Небольшой пример - создадим архив с названием archive.zip, в котором
 * будут находиться сжатые файлы из директории SomeDir.
 * В этом примере пустые директории будут игнорироваться.
 * Уровень компрессии явно не задан, поэтому будет использоваться значение по-умолчанию.
 * http://www.javenue.info/post/35
 * Чтобы создать новый архив необходимо воспользоваться классом ZipOutputStream.
 * Вот список методов, которые могут понадобиться:
 * - setLevel - установка уровня компрессии от 0 до 9, где 9 - максимальная компрессия;
 * - putNextEntry - вызывается перед записью нового объекта в архив, с указанием
 * имени объекта;
 * - closeEntry - вызываем после записи объекта. putNextEntry автоматически вызывает
 * метод closeEntry.
 * - close - закрываем поток.
 *
 * Обратите внимание, что при создании ZipEntry мы использовали относительный путь,
 * а не просто имя файла. Это сделано для того, чтобы при архивации сохранились
 * все дерево директорий, ведущих к файлу. В случае использования f.getName()
 * в архиве просто будет плоский список файлов без информации о директориях.
 * */

public class ZipUtil2 {
    public static void main(String[] args) throws Exception {
//        ZipOutputStream out = new ZipOutputStream(new FileOutputStream("C:/test/archive.zip"));
        //ZipOutputStream out = new ZipOutputStream(new FileOutputStream("./archiveChapter_005.zip"));

//        String folder = "C:/test/SomeDir";
        String folder = "./chapter_005";
        String folder2 = "./chapter_004";
        String folder3 = "./chapter_003";
        File file = new File(folder);
        File file2 = new File(folder2);
        File file3 = new File(folder3);

        //doZip(file, out);

        //out.close();

//        doZip2(file, new File("./newArchChapter_005.zip"));
//        doZip2(file, new File("./NEWArchChapter_005.zip"));
//        doZip2(file2, new File("./NEWArchChapter_004.zip"));
        doZip2(file3, new File("./NEWArchChapter_003.zip"));

//        ZipOutputStream out = new ZipOutputStream(new FileOutputStream("./NewArchiveChapter_004.zip"));
//        doZip(file2, out);

    }

    private static void doZip(File dir, ZipOutputStream out) throws IOException {
        for (File f : dir.listFiles()) {
            if (f.isDirectory())
                doZip(f, out);
            else {
                out.putNextEntry(new ZipEntry(f.getPath()));
                write(new FileInputStream(f), out);
            }
        }
    }

    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
        in.close();
    }

    private static void doZip2(File dir, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target))
        )) {
            try {
                doZip(dir, zip);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
