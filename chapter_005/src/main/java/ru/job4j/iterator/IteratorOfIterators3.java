package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorOfIterators3 {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> inner = it.next();

            @Override
            public boolean hasNext() {
                if (inner == null) {
                    return false;
                }
                while (!inner.hasNext() && it.hasNext()) {
                    inner = it.next();
                }
                return inner.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (inner.hasNext()) {
                    return inner.next();
                } else {
                    inner = it.next();
                    return inner.next();
                }
            }
        };
    }
}
