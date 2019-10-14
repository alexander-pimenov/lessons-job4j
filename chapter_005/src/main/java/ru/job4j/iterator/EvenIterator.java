package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * EvenIterator
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class EvenIterator implements Iterator {
    private Integer[] values;
    private Integer i = 0;
    private boolean nextIsKnown = false;

    public EvenIterator(Integer[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return Arrays.stream(values).anyMatch(value -> {
            if (value % 2 == 0 && value > i) {
                i = value;
                nextIsKnown = true;
            }
            return nextIsKnown;
        });
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            nextIsKnown = false;
            return i;
        }
    }
}
