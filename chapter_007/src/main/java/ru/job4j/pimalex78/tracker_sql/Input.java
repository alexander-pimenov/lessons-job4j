package ru.job4j.pimalex78.tracker_sql;

public interface Input {
    String ask(String question);

    int ask(String question, int[] range);
}
