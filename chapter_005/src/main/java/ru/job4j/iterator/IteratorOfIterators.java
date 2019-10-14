package ru.job4j.iterator;

import java.lang.Integer;
import java.util.Iterator;

public class IteratorOfIterators {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> inner = it.next();

            @Override
            public boolean hasNext() {
                while (!inner.hasNext() && it.hasNext()) {
                    inner = it.next();
                }
                return inner.hasNext();
            }

            @Override
            public Integer next() {
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
