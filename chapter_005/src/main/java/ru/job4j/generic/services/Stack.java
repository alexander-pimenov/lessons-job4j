package ru.job4j.generic.services;

/**
 * Класс Stack наследует наш класс SimpleList<> и
 * нем будет определятся тип генерика.
 * Задаем тип String.
 * Из этого класса мы можем теперь получить генерик
 * в классе родителя, в SimpleList<>.
 */

public class Stack<E> extends SimpleList<E> {

    //конструктор.
    public Stack(int size) {
        super(size);
    }
}
