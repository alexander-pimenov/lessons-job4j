package ru.job4j.io.exam;

import org.apache.commons.cli.ParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Logic
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private static final String LS = System.getProperty("line.separator");

    public static void main(String[] args) throws ParseException, IOException {
        //".*readme\\.{1}txt"
        Args params = new Args(args);
        Logic logic = new Logic();
        logic.search(params);
    }

    public void search(Args args) throws IOException {
        List<File> listForLog;
        if (args.match()) {
            listForLog = this.filterMatch(args.directory(), args.name());
        } else if (args.fullMatch()) {
            listForLog = this.filterFullMatch(args.directory(), args.name());
        } else {
            listForLog = this.filterRegular(args.directory(), args.name());
    }
        this.writeLog(args.output(), listForLog);
    }

    private List<File> getFilesFrom(String source) {
        List<File> result = new LinkedList<>();
        File start = new File(source);
        Queue<File> data = new LinkedList<>();
        File[] deepDirectory = null;

        data.offer(start);
        while (!data.isEmpty()) {
            File file = data.poll();
            if (file != null && file.isDirectory()) {
                deepDirectory = file.listFiles();
                if (deepDirectory != null) {
                    for (File value : deepDirectory) {
                        data.offer(value);
                    }
                }
            } else {
                result.add(file);
            }
        }
        return result;
    }

    public List<File> filterFullMatch(String source, String name) {
        List<File> result = new ArrayList<>();
        List<File> preparedList = getFilesFrom(source);
        for (File file : preparedList) {
            if (file.getName().equalsIgnoreCase(name)) {
                result.add(file);
            }
        }
        return result;
    }

    public List<File> filterMatch(String source, String name) {
        List<File> result = new ArrayList<>();
        List<File> preparedList = getFilesFrom(source);
        for (File file : preparedList) {
            String fileName = file.getName();
            if (fileName.contains(name)
                    || fileName.equalsIgnoreCase(name)
                    || fileName.endsWith(name)
                    || fileName.startsWith(name)) {
                result.add(file);
            }
        }
        return result;
    }

    public List<File> filterRegular(String source, String regular) {
        Pattern regEx = Pattern.compile(regular, Pattern.CASE_INSENSITIVE);
        List<File> result = new ArrayList<>();
        List<File> preparedList = getFilesFrom(source);
        for (File file : preparedList) {
            Matcher matcher = regEx.matcher(file.getName());
            if (matcher.matches()) {
                result.add(file);
            }
        }
        return result;
    }

    private void writeLog(String nameOfLogFile, List<File> files) throws IOException {
        File dirSearchLog = new File(System.getProperty("java.io.tmpdir") + "/SearchLog");
        File log = new File(dirSearchLog.getAbsolutePath(), nameOfLogFile);
        dirSearchLog.mkdir();
        log.createNewFile();
        FileWriter fileWriter = new FileWriter(log, false);
        for (File file : files) {
            fileWriter.write(file.getAbsolutePath() + LS);
        }
        fileWriter.flush();
        fileWriter.close();

    }
}

