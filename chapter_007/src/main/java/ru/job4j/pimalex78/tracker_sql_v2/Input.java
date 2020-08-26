package ru.job4j.pimalex78.tracker_sql_v2;

public interface Input {
    String ask(String question);

    int ask(String question, int[] range);
}
