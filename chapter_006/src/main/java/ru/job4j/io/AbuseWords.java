package ru.job4j.io;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * AbuseWords
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class AbuseWords {

    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            while (reader.ready()) {
                reader.lines()
                        .map(line -> Arrays.stream(abuse)
                                .reduce(line, (words, badWord) -> words.replaceAll(badWord, ""))
                        ).map(String::trim).forEach(line -> {
                    try {
                        writer.write(line);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}