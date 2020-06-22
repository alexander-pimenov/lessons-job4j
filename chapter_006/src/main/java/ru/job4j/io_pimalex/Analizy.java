package ru.job4j.io_pimalex;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))
        ) {
            String currentLine = null;
            while (reader.ready()) {
                String data = reader.readLine();
                if (currentLine == null && (data.startsWith("400") || data.startsWith("500"))) {
                    writer.write(data.split(" ")[1] + ";");
                    currentLine = data;
                } else if (currentLine != null && (!data.isEmpty() && !data.startsWith("400")
                        && !data.startsWith("500"))) {
                    writer.write(data.split(" ")[1]);
                    writer.newLine();
                    currentLine = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unavailable2(String source, String target) {
        List<String> listOfInterval = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith("500") || currentLine.startsWith("400")) {
                    StringBuilder interval =
                            new StringBuilder(currentLine.split(" ")[1]).append(";");
                    while ((currentLine = reader.readLine()) != null) {
                        if (currentLine.startsWith("200") || currentLine.startsWith("300")) {
                            interval.append(currentLine.split(" ")[1]).append("\n");
                            listOfInterval.add(interval.toString());
                            break;
                        }
                    }
                }
            }
            for (String line : listOfInterval) {
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }


        Analizy analizy = new Analizy();

        /*рассмотрены два метода, что бы показать что выдают они один и тот же результат*/
        analizy.unavailable("source.txt", "target.txt");
        analizy.unavailable2("source.txt", "target2.txt");
    }
}
