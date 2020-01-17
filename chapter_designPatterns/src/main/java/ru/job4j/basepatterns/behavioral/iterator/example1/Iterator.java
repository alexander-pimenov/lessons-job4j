package ru.job4j.basepatterns.behavioral.iterator.example1;

/**
 * Итератор описывает интерфейс для доступа и обхода коллекции.
 */

public interface Iterator {
    public boolean hasNext();

    public Object next();
}
