package ru.job4j.pimalex78.tracker_sql;

public interface UserAction {
    int key();

    void execute(Input input, ITracker tracker);

    String info();
}
