package ru.job4j.yandex;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MainYandex2 {

    public static void main(String[] args) throws IOException {

        String fileName = "c:\\test\\test.txt";
        // используем класс Scanner для больших файлов, читаем построчно
        //Если вам нужно считывать с файла, опираясь на разделитель, то
        //желательно использовать класс Scanner.
        System.out.println("\nЧтение файла спомощью Scanner:");
        readUsingScanner(fileName);

    }

    private static void readUsingScanner(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);

        int i = 0;
        //читаем построчно
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();


            // обрабатываем считанную строку - пишем ее в консоль
            System.out.println(line);
        }
        scanner.close();

        //продолжаем дальше работать с данными из файла


    }

}

/*public class ReadFile {

    private Scanner x;

    //метод для открытия файла
    public void openFile() {
        try {
            x = new Scanner(new File("c:\\test\\chik.txt"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Could not file");
        }
    }

    //метод чтения из файла
    //тоже обернули в try-catch,т.к. если файла такого нет то его
    //и не открыть и не прочитать
    public void readFile() {
        try {
            //прочитать содержимое. читать пока есть содержимое
            while (x.hasNext()) {
                String a = x.next();
                String b = x.next();
                String c = x.next();

                System.out.printf("%s %s %s\n", a, b, c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Could not file");
            //e.printStackTrace();
        }
    }

    //метод закрытия файла
    public void closeFile(){
        x.close();
    }
}*/
