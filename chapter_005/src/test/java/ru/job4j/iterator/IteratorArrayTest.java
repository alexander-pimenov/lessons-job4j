package ru.job4j.iterator;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Мы работаем по принципу TDD (Test Drive Development), т.е.
 * у нас есть возможность писать тесты.
 * Через тесты проверяем правильность кода, а не через метод main().
 */

public class IteratorArrayTest {
    //Создадим внутренний статический финальный класс для демонстрации работы
    //цикла forEach. Т.е. мы его не тестируем, а просто смотрим на его работу.
    static final class ForEachArray implements Iterable {
        //Создаем входящий массив
        private final int[] values;

        //Создаем конструктор.
        public ForEachArray(final int[] values) {
            this.values = values;
        }

        public Iterator iterator() {
            return new IteratorArray(this.values);
        }
    }

    //В тесте мы проверяем поведение метода next() и метода hasNext(),
    //Если мы вызываем next(), то наша каретка должна сдвигаться на одну позицию
    //вперед.
    @Test
    public void whenNextCallShouldPointerForward() {

        //Действуем по принципу "Три A(эй)"

        //Блок Организации: Block arrange.
        //В параметр передаем входящий массив и будем использовать минимальный набор: {1,3};
        IteratorArray it = new IteratorArray(new int[]{1, 3});

        //Блок Действие: Block Act.
        //Приводим к типу Integer, т.к. метод возвращает Object
        //Если использовать Generic, то было бы проще.
        it.next();
        int result = (Integer) it.next();

        //Блок утверждения: Block assert.
        //Производим проверку реального значения result и ожидаемого значения,
        //ожидаемое значение должно быть "3", т.к. next() вызывали два раза.
        assertThat(result, is(3));
    }

    //Проверяем метод hasNext(). Используем минимальный набор входящего массива: {1};
    @Test
    public void whenCheckNextPositionShouldReturnConstantValue() {

        IteratorArray it = new IteratorArray(new int[]{1});

        it.next();
        it.hasNext();
        //вызываем метод два раза, чтобы видеть будет возвращаться одно и тоже значение false
        //и метод hasNext() не влияем на положение указателя.
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }

    //Проверяем работу цикла forEach.
    //Это не тест, мы это используем для того, чтобы запустить цикл forEach
    //и посмотреть на его работу.
    @Test
    public void foreach() {

        ForEachArray foreach = new ForEachArray(new int[]{1, 4, 5});

        for (Object value : foreach) {
            System.out.println(value);
        }
        //Вывод результата:
        //1
        //4
        //5
    }
}