package ru.job4j.yandex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Здесь требуется вывести текст файла в консоль,
 * для этого лучше выводить его построчно через BufferedReader.
 */

//public class FileRead {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        File f = new File("c:\\test\\test_Files.txt");
//        BufferedReader fin = new BufferedReader(new FileReader(f));
//        String name;
//        String line;
//        System.out.println("Print File "+f.getName()+"? y/n");
//        name = br.readLine();
//        if(name.equals("y"))
//            while ((line = fin.readLine()) != null) System.out.println(line);
//    }
//}

//public class FileReadAndWrite {
public class FileRead {
    public static void main(String[] args) throws IOException {

        File file = new File("c:\\test\\test_Files2.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        List<String> arr = new ArrayList<>();

        while ((line = br.readLine()) != null) {

            arr.add(line);
        }
        br.close();

        int sum = arr.stream().mapToInt(Integer::parseInt).sum();

        String resultSum = Integer.toString(sum);

        /*Запись данных в файл.*/
        try {

            // Если файл не существует, то создадим его
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("\n" + resultSum);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





