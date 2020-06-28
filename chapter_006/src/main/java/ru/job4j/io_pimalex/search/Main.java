package ru.job4j.io_pimalex.search;

import java.util.function.Predicate;

/*пример работы с предикатом*/
public class Main {
    public static void main(String[] args) {

        Predicate<String> isPositive = x -> x.endsWith("aa");
        System.out.println(isPositive.test("aaaaqw"));
        System.out.println(isPositive.test("aaaaaaa"));
    }
}
