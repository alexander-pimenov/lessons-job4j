package ru.job4j.tree.tree_arhiser;

import java.util.ArrayList;

/**
 * Очередь обеспечивает процесс FIFO -
 * первый пришел, первый вышел.
 * @param <T>
 */

public class SimpleQueue<T> implements Queue<T> {
    //Реализуем Очередь на основе ArrayList
    //Скроем всю реализацию листа за интерфейсом
    private ArrayList<T> list = new ArrayList<>();

    //Добавляем элемент в конец списка
    @Override
    public void add(T item) {
        list.add(item);
    }

    //Удаляем элемент из начала списка
    @Override
    public T remove() {
        return list.remove(0);
    }

    //проверяем пустая ли очередь (лист)
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
