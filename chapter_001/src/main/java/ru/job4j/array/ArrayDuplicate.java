package ru.job4j.array;

import java.util.Arrays;
/**
 * ArrayDuplicate
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicate {
    /**
     * Delete duplicate.
     * @param array - array of strings.
     * @return array without duplicates.
     */
    public String[] remove(String[] array) {
        int n = array.length;
        for (int out = 0; out < n; out++) {
            for (int inner = out + 1; inner < n; inner++) {
                if (array[out].equals(array[inner])) {
                    array[inner] = array[n - 1];
                    n--;
                    inner--;
                }
            }
        }
        return Arrays.copyOf(array, n);
    }
}
