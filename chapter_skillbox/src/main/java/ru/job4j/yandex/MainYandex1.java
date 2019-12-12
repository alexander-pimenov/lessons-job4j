package ru.job4j.yandex;

import java.util.Scanner;

public class MainYandex1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число a:");
        int a = sc.nextInt();
        System.out.println("Введите число b:");
        int b = sc.nextInt();

        System.out.println("a + b = " + (a + b));

        int aa = 5;
        int bb = 10;
        System.out.println(aa + " " + bb + " " + (aa + bb));
    }
}

