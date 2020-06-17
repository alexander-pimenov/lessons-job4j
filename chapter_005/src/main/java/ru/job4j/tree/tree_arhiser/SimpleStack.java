package ru.job4j.tree.tree_arhiser;

import java.util.ArrayList;

public class SimpleStack<T> implements Stack<T> {
    //Реализуем Стек на основе ArrayList
    //Скроем всю реализацию листа за интерфейсом
    private ArrayList<T> list = new ArrayList<>();

    //Добавляем элемент в начало списка
    @Override
    public void push(T item) {
        list.add(0, item);
    }

    //Удаляем элемент из начала списка
    @Override
    public T pop() {
        return list.remove(0);
    }

    //Проверяем пустой ли стек (лист)
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(T item) {
        return list.contains(item);
    }
}
