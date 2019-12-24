package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Реализуем класс IteratorArray имплементирующий интерфейс Iterator.
 * Благодаря нашей собственной реализации, мы можем сами задать для себя,
 * как нам нужно двигаться по элементам массива.
 * Другими словами, Итератор - это получение последовательного доступа
 * ко всем элементам составного объекта, скрывая его
 * внутреннее представление.
 * Пример использования:
 * - различные виды обхода составного объекта;
 * - упрощённый доступ к составному объекту.
 *
 * @author pimalex1978
 * @since 13.12.2019
 */

public class IteratorArray implements Iterator {
    //Входящий параметр – это массив.
    private final int[] values;

    //Также создаем индекс, который начинается с позиции 0.
    private int index = 0;

    //Создаем конструктор, принимающий наши параметры.
    public IteratorArray(final int[] values) {
        this.values = values;
    }

    //Здесь будем проверять, что размер нашего массива меньше, чем index.
    //Соответственно, если это правильно, то метод hasNext() будет реализовываться.
    //Помним, что после вызова метода next(), индекс увеличивается на 1.
    //Если не будем вызывать next(), то а только hasNext(), то и индекс не будет изменяться.
    public boolean hasNext() {
        //System.out.println(index); //Чтобы посмотреть какой имеем номер индекса.
        return values.length > index;
    }

    //В этом методе мы двигаемся по каждому элементу, и на выходе получаем каждый элемент,
    //и переводим каретку на следующий элемент массива.
    //А можно двигаться только по четным числам. Всё зависит от нашей реализации.
    //Т.е. есть входящий массив и итератор так реализован,
    //что на выходе можем получать только четные числа.
    public Object next() {
        return values[index++];
    }

}
