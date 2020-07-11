package ru.job4j.io_pimalex.zip;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.StringJoiner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.junit.Assert.*;

public class ZipTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void zipTest() throws IOException {
        File target = folder.newFile("pom.zip");
        File source = folder.newFile("pom.xml");
        StringJoiner text = new StringJoiner(System.lineSeparator());
        text.add("analizy.txt").add("notanalizy.jpeg").add("notanalizy.raw");
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(source)))) {
            zip.putNextEntry(new ZipEntry(source.getAbsolutePath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }

            var args = new String[]{"-d", "pom.xml", "-e", "txt", "-o", "pom.zip"};
//            new Zip().packFiles(Zip.findFiles(args));
        }
    }

}