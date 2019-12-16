package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Создаем итератор возвращающий только четные цифры.
 * Итератор может принимать список произвольных чисел.
 */

public class EvenNumbersIterator implements Iterator {

    private final int[] values;
    private int index = 0;

    public EvenNumbersIterator(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        while (index < values.length) {
            if (values[index] % 2 == 0) {
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    /*  //Можно использовать и этот метод для next(),
        //чтобы избежать цикла while() в методе next(),
        //Можем раскомментировать и всё будет исправно работать.
        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            var result = values[index];
            index++;
            return result;
        }
    */
    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (values[index] % 2 != 0) {
            index++;
        }
        return values[index++];
    }
}
