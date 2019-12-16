package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            private Iterator<Integer> inner = it.next();

            @Override
            public boolean hasNext() {
                return inner.hasNext();
            }

            @Override
            public Integer next() {
                Integer n;
                if (this.hasNext()) {
                    n = inner.next();
                    if (!inner.hasNext() && it.hasNext()) {
                        inner = it.next();
                    }
                } else {
                    throw new NoSuchElementException();
                }
                return n;
            }
        };
    }
}
