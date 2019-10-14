package ru.job4j.io.zip;

import org.apache.commons.cli.*;
import ru.job4j.tracker.ConsoleInput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Logic
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    public static void main(String[] args) throws ParseException {
        Args params = new Args(args);
        Logic logic = new Logic();
        logic.zipping(params);
    }

    public void zipping(Args args) {
        List<File> listForZipping = this.filter(args.directory(), args.exclude());
        File files = new File(System.getProperty("java.io.tmpdir") + "\\" + args.output());
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(files, false))) {
            for (File file : listForZipping) {
                FileInputStream fis = new FileInputStream(file);
                zip.putNextEntry(new ZipEntry(file.getAbsolutePath().substring(args.directory().length() + 1)));
                byte[] buffer = fis.readAllBytes();
                zip.write(buffer);
                fis.close();
                zip.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<File> list(String source) {
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

    private List<File> filter(String source, String exclude) {
        List<File> result = new LinkedList<>();
        List<File> preparedList = list(source);
        for (File file : preparedList) {
                if (!file.getName().endsWith(exclude)) {
                    result.add(file);
            }
        }
        return result;
    }
}