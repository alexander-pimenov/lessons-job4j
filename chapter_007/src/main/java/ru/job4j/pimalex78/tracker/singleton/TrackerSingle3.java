package ru.job4j.pimalex78.tracker.singleton;

import ru.job4j.pimalex78.tracker.start.MemTracker;

/**
 * Шаблон Синглтон для создания трекера в единственном
 * экземпляре.
 * Вариант 3.
 */
public class TrackerSingle3 extends MemTracker {
    private static final TrackerSingle3 INSTANCE = new TrackerSingle3();

    private TrackerSingle3() {
    }

    public static TrackerSingle3 getINSTANCE() {
        return INSTANCE;
    }
}
