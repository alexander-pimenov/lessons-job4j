package ru.job4j.tutorial.stack_queue;

public interface Queue<T> {
    void add(T item); // добавить элемент в конец очереди

    T remove(); // извлечение элемента из начала очереди

    boolean isEmpty(); // чтобы проверить, что очередь пуста
}
