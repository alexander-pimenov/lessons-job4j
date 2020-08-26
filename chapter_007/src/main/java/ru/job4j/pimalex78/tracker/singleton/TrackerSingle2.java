package ru.job4j.pimalex78.tracker.singleton;

import ru.job4j.pimalex78.tracker.start.MemTracker;

/**
 * Шаблон Синглтон для создания трекера в единственном
 * экземпляре.
 * Вариант 2.
 */
public class TrackerSingle2 extends MemTracker {
    private static TrackerSingle2 instance;

    private TrackerSingle2() {
    }

    public static TrackerSingle2 getInstance() {
        if (instance == null) {
            instance = new TrackerSingle2();
        }
        return instance;
    }
}
