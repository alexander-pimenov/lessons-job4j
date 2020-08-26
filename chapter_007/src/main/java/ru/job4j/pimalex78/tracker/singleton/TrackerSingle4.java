package ru.job4j.pimalex78.tracker.singleton;

/**
 * Шаблон Синглтон для создания трекера в единственном
 * экземпляре.
 * Вариант 4.
 */
public class TrackerSingle4 {
    private TrackerSingle4() {
    }

    public static TrackerSingle4 getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final TrackerSingle4 INSTANCE = new TrackerSingle4();
    }
}
