package ru.job4j.io_pimalex.search_by_criteria_v1;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsParser {
    /**
     * Поле - хранит путь начального файла для архивации.
     */
    private String directory;
    /**
     * Поле - хранит название искомого файла, либо его часть, либо полное название,
     * либо расширение.
     */
    private String searchFile;
    /**
     * Поле - хранит путь к лог-файлу, в который результат поиска будет записан.
     */
    private String output;

    private final String[] args;

    private Map<String, String> arguments = new HashMap<>();

    //классы для работы с регулярными выражениями
    private Pattern p = null;
    private Matcher m = null;

    ArgsParser(String[] args) throws Exception {
        this.args = args;
        parseArguments();
    }

//     java -jar find.jar -d c:\project\job4j\ -n class -m [mfr] -o search_results.txt


    /**
     * Проверяем соответствие входящего args необходимым критериям.
     */
    public boolean valid() {
        boolean result = true;
        List<String> argsList = Arrays.asList(args);
//        if (!argsList.contains("-d")
//                && !argsList.contains("-n")
//                && !argsList.contains("-o")) {
//            System.out.println("Args is not valid.");
//            result = false;
//        }
        if (!argsList.contains("-d")) {
            System.out.println("Args is not valid. Не введена директория поиска файлов.");
            result = false;
        } else if (!argsList.contains("-n")) {
            System.out.println("Args is not valid. Не введен параметр поиска файла.");
            result = false;
        } else if (!argsList.contains("-o")) {
            System.out.println("Args is not valid. Не введен файл для записи результата.");
            result = false;
        } else if (!args[4].equals("-m")) {
            if (!args[4].equals("-f")) {
                if (!args[4].equals("-r")) {
                    System.out.println("Args is not valid. Не верный ключ для поиска.\r\n"
                            + "Должен быть один ключ или m или f или r.");
                    result = false;
                }
            }
        }
        return result;
    }

    private void parseArguments() throws Exception {
        int countArguments = 7;
        if (args.length < countArguments) {
            throw new IllegalArgumentException("Invalid startup options. Fix it.");
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-m")
                    || args[i].equals("-f")
                    || args[i].equals("-r")) {
                arguments.put(args[i], "");
            } else if (args[i].equals("-d")
                    || args[i].equals("-n")
                    || args[i].equals("-o")) {
                arguments.put(args[i], args[i + 1]);
            }
        }
        if (!valid()) {
            throw new IllegalArgumentException("Invalid startup options");
        }
        String directory = arguments.get("-d");
        File topDirectory = new File(directory);
        if (!topDirectory.exists()) {
            throw new Exception("Error: The specified source directory path does not exist.");
//            throw new Exception("Ошибка: указанный путь исходной директории не существует.");
        }
        if (!topDirectory.isDirectory()) {
            throw new Exception("Error: The specified path is not a directory.");
//            throw new Exception("Ошибка: указанный путь - это не директория.");
        }
    }

    /**
     * Метод возвращает путь начального файла для архивации.
     *
     * @return путь начального файла.
     */
    public String getDirectory() {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                directory = args[++i];
//                directory = new File(args[++i]).getAbsolutePath();
                break;
            }
        }
        return directory;
//        return arguments.get("-d");
    }

    /**
     * Метод возвращает название искомого файла:
     * его расширение или часть от названия или полное название.
     *
     * @return искомый файл
     */
    public String getSearchFile() {
//        List<String> result = new LinkedList<>();
//
//        for (int i = 0; i < args.length; i++) {
//            if (args[i].equals("-n")) {
//                result = Arrays.asList(args[++i].split(","));
//                break;
//            }
//        }
//        return result;
        return arguments.get("-n");
    }

    /**
     * Метод возвращает путь к файлу записи результата поиска.
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
//        return arguments.get("-o");
    }

    public Predicate<String> flagCheck() {
        Predicate<String> filePredicate = null;
        if (arguments.containsKey("-r")) {
//            filePredicate = str -> (str.matches(preparePattern(getSearchFile()))); //сравнение по регулярному выражению
            filePredicate = str -> {
//                Pattern p = null;
//                Matcher m = null;
                String searchFile = getSearchFile();
//                System.out.println("Паттерн для поиска файла: " + searchFile);
                String pattern = preparePattern(searchFile);
//                System.out.println("Паттерн для поиска файла после преобразования: " + pattern);
                p = Pattern.compile(pattern, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
//                p = Pattern.compile(getSearchFile(), Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
                m = p.matcher(str);
                return m.matches();
            }; //сравнение по регулярному выражению
        } else if (arguments.containsKey("-f")) { //сравнение по полному имени
            filePredicate = str -> str.equals(getSearchFile());
        } else if (arguments.containsKey("-m")) { //сравнение по маске (расширение, начало имени, конец имени, часть имени)
            filePredicate = str -> str.endsWith(getSearchFile())
                    || str.startsWith(getSearchFile())
                    || str.equalsIgnoreCase(getSearchFile())
                    || str.contains(getSearchFile());
        }
        return filePredicate;
    }

    // *.xml .xml xml
    private static String preparePattern(String pattern) {

        if (pattern.contains("*.")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pattern.length(); i++) {
                char c = pattern.charAt(i);
                if (c == '*') {
                    sb.append(".+");
                } else if (c == '.') {
                    sb.append("\\.");
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return pattern;
    }
}
