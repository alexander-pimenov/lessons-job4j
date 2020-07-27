package ru.job4j.io_pimalex.search_by_criteria_v1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s;
        do {
            System.out.println("Enter word for checking");
            System.out.println("Only Или pizza или @pizza или pizza3");
            s = scanner.nextLine();
            System.out.println("Вы ввели: " + s);
            System.out.println("Результат вызова метода test:");
            System.out.println(test(s));
            System.out.println("Хотите продолжить: любой символ (Да) / n|N (Нет)");
            s = scanner.nextLine();
            if (s.equals("n")) {
                System.out.println("Вы выбрали выйти. Выходим.");
            } else {
                System.out.println("Вы выбрали продолжать. Продолжаем.");
            }
        } while (!s.equalsIgnoreCase("n"));//выход если в параметре получаем false
//        System.out.println(test("pizza"));
//        System.out.println(test("@pizza"));
//        System.out.println(test("pizza3"));


    }

    public static boolean test(String testString) {
        String pattern = "^[a-z]+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(testString);
        return m.matches();
    }
}
