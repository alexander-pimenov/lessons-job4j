package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс с методот converter() для преобразования Итератора итераторов
 * в Итератор чисел, т.е. на вход метод получает объект Итератор итераторов
 * и возвращает Итератор чмисел.
 * Например,
 * На вход подаем Iterator<Iterator<Integer>> - ((4 2 0 4 6 4 9),(0 9 8 7 5),(1 3 5 6 7 0 9 8 4))
 * Метод возвращает Iterator<Integer> - (4 2 0 4 6 4 9 0 9 8 7 5 1 3 5 6 7 0 9 8 4)
 */

public class IteratorOfIterators2 {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> temp;
            Iterator<Iterator<Integer>> iterator = it;

            @Override
            public boolean hasNext() {
                boolean valid = false;
                if (temp == null || !temp.hasNext()) {
                    while (iterator.hasNext()) {
                        temp = iterator.next();
                        if (temp.hasNext()) {
                            valid = true;
                            break;
                        }
                    }
                } else if (temp.hasNext()) {
                    valid = true;
                }
                return valid;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (!temp.hasNext() && iterator.hasNext()) {
                    temp = iterator.next();
                }
                return temp.next();
            }
        };
    }
}
