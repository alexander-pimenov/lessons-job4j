package ru.job4j.pimalex78.tracker.start;

import java.util.List;

public interface Input {
    String ask(String question);

    int ask(String question, List<Integer> range);
}
