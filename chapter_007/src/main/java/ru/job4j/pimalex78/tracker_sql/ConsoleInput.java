package ru.job4j.pimalex78.tracker_sql;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (key == value) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Выход за границы допустимых значений");
        }
    }
}
