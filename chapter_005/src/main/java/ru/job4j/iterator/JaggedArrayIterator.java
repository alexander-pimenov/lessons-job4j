package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * JaggedArrayIterator
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class JaggedArrayIterator<Integer> implements Iterator<Integer> {
    private Integer[][] values;
    private int i = 0;
    private int j = 0;

    public JaggedArrayIterator(Integer[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return i < values.length && j < values[i].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            Integer result = values[i][j++];
            if (j > values[i].length - 1) {
                i++;
                j = 0;
            }
            return result;
        }
    }
}
