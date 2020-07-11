package ru.job4j.io_pimalex.zip;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArgZip {
    /**
     * Поле - хранит путь начального файла для архивации.
     */
    private String directory;
    /**
     * Поле - хранит исключающие расширение файла.
     */
    private List<String> exclude;
//    private String exclude;
    /**
     * Поле - хранит путь расположение файла после архивации .
     */
    private String output;


    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    //    /**
//     * java -jar pack.jar -d=c:\project\job4j\ -e=class -o=project.zip
//     * Instantiates a new Args.
//     * -d - directory - которую мы хотим архивировать
//     * -e - exclude - исключить файлы *.xml
//     * -o - output - во что мы архивируем.
//     *
//     * @param args the args
//     */
//    public ArgZip(String[] args) {
//        for (int i = 0; i < args.length; i++) {
//            if (args[i].equals("-d")) {
//                this.directory = args[++i];
//            } else if (args[i].equals("-e")) {
//                while (!args[i + 1].equals("-o")) {
//                    if (args[i + 1].startsWith("*")) {
//                        this.exclude.add(args[i + 1].substring(1));
//                    } else {
//                        this.exclude.add(args[i + 1]);
//                    }
//                    i++;
//                }
//            } else if (args[i].equals("-o")) {
//                this.output = args[i + 1];
//            }
//        }
//        //this.args = args;
//    }

    /**
     * Проверяем соответствие входящего args необходимым критериям.
     * Строго:
     * Нулевым: -d,
     * Вторым: -e,
     * Пред-последним: -o.
     */
    public boolean valid() {
        boolean result = true;
        List<String> stringList = Arrays.asList(args);
        if (!stringList.contains("-d")
                || !stringList.contains("-o")) {
            System.out.println("Args is not valid.");
            result = false;
        }
        return result;
    }

    /**
     * Метод возвращает путь начального файла.
     *
     * @return путь начального файла.
     */
    public String getDirectory() {
//        for (int i = 0; i < args.length - 1; i = i + 2) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                directory = new File(args[++i]).getAbsolutePath();
                break;
            }
        }
        return directory;
    }

    /**
     * Метод возвращает файл с исключающим расширением.
     *
     * @return файл с исключающем расширением
     */
    public List<String> getExclude() {
        List<String> result = new LinkedList<>();

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-e")) {
                result = Arrays.asList(args[++i].split(","));
//                exclude = args[i + 1].split("\\.")[1];
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает путь расположение файла после архивации.
     *
     * @return путь расположение файла после архивации
     */
    public String getOutput() {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-o")) {
                output = args[++i];
                break;
            }
        }
        return output;
    }
}
