package ru.job4j.io_pimalex.zip;

import ru.job4j.io_pimalex.search.Search3;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    /*Метод упаковывающий в архив несколько файлов.*/

    /**
     * Метод - архивирует проект.
     *
     * @param sources - список файлов без заданного расширения.
     * @param target- путь хранения архива.
     */
    public void packFiles(List<File> sources, File target) {

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


    /*Метод упаковывающий в архив 1 файл.*/
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Path> findFiles2(ArgZip argZip) throws IOException {

        List<String> exclude = argZip.getExclude();

//        System.out.println("начало обработки списка файлов");

        List<Path> searchedFiles = new LinkedList<>();
        List<File> search = new Search3().filter(argZip.getDirectory(), exclude);
//        System.out.println("вывод списка файлов");
//        System.out.println("Список пуст? : " + search.isEmpty());
        for (File path : search) {
            Path path1 = path.toPath();
            searchedFiles.add(path1);
//            System.out.println(path1);
        }
//        System.out.println("конец вывода списка файлов");
        return searchedFiles;
    }

    /*В методе проставлены sout("") для того чтобы нагляднее было видно, какие операции производятся с файлами.*/
    public static List<File> findFiles3(ArgZip argZip) throws IOException {
        String directory = argZip.getDirectory();
        List<String> exclude = argZip.getExclude();
        List<File> result = new ArrayList<>();
        Queue<File> list = new LinkedList<>();
        list.offer(new File(directory));
        System.out.println("#list.offer(new File(directory)) = " + list);
        while (!list.isEmpty()) {
            System.out.println("START loop while");
            File file = list.poll();
            System.out.println("-##list.poll() = " + file);
            if (file != null && file.isDirectory() && file.canRead()) {
                list.addAll(Arrays.asList(Objects.requireNonNull(file.listFiles())));
//                list.addAll(Arrays.asList(file.listFiles()));
                System.out.println("--BLOCK <if>");
                System.out.println("---###list.addAll(Arrays.asList(file.listFiles()))" + list);
            } else if (checkName(file, exclude)) {
                result.add(file);
                System.out.println("----BLOCK <else if>");
                System.out.println("-----####result.add(file) = " + result);
            }
            System.out.println("FINISH loop while");
        }
        return result;
    }

    private static boolean checkName(File file, List<String> ext) {
        /*меняя result = false на true, мы либо получаем список указанных файлов, либо список без этих файлов*/
//        boolean result = false;
        boolean result = true; //файлы исключаются
        for (String s : ext) {
            if (file.getName().endsWith(s)) {
//                result = true;
                result = false; //файлы исключаются
                break;
            }
        }
        return result;
    }

    /*Метод упаковывает полную директорию, как она есть, в указанный файл*/
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

    public static void main(String[] args) throws IOException {
//        new Zip().packSingleFile(
//                new File("./chapter_005/pom.xml"),
//                new File("./chapter_005/pom.zip")
//        );

        args = new String[]{"java", "-jar", "pack.jar", "-d", "c:/projects/lessons-job4j/chapter_004", "-e", "class,xml", "-o", "project_chapter_004_v3.zip"};
        String[] args2 = new String[]{"-d", "c:/projects/lessons-job4j/chapter_004", "-e", "class", "-o", "projects_chapter_004.zip"};

        ArgZip argZip = new ArgZip(args);
        ArgZip argZip2 = new ArgZip(args2);
//        System.out.println(argZip.valid());
//        String directory = argZip.getDirectory();
//        System.out.println(argZip.getDirectory());
//        String output = argZip.getOutput();
//        System.out.println(argZip.getOutput());
//        List<String> exclude = argZip.getExclude();
//        System.out.println("list exclude : " + argZip.getExclude());

//        System.out.println("===findFiles2====");
//        List<Path> pathList = findFiles2(argZip);
//        findFiles2(argZip).forEach(System.out::println);
//        pathList.stream().map(Path::toFile).collect(Collectors.toList());
//        List<File> fileList2 = pathList.stream().map(Path::toFile).collect(Collectors.toList());
//        new Zip().packFiles(fileList2, new File(argZip.getOutput()));

        System.out.println("===findFiles3====");
//        findFiles3(argZip2).forEach(System.out::println);
        findFiles3(argZip2);
//        List<File> filesList = findFiles3(argZip);
//        List<File> filesList2 = findFiles3(argZip2);
//        new Zip().packFiles(filesList, new File(argZip.getOutput()));
//        new Zip().packFiles(filesList2, new File(argZip2.getOutput()));


    }
}
